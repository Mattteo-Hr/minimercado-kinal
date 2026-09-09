package com.kinal.mercado.dao;

import com.kinal.mercado.config.ConnectionDb;
import com.kinal.mercado.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {

    public void agregarProducto(Producto p) {
        String sql = "INSERT INTO Producto (codigoBarras, nombreComercial, precioCosto, precioVenta, categoria, stock) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = ConnectionDb.getInstancia().getConexion().prepareStatement(sql)) {
            stmt.setString(1, p.getCodigoBarras());
            stmt.setString(2, p.getNombreComercial());
            stmt.setDouble(3, p.getPrecioCosto());
            stmt.setDouble(4, p.getPrecioVenta());
            stmt.setString(5, p.getCategoria());
            stmt.setInt(6, p.getStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Producto";
        try (Statement stmt = ConnectionDb.getInstancia().getConexion().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = new Producto(
                    rs.getString("codigoBarras"),
                    rs.getString("nombreComercial"),
                    rs.getDouble("precioCosto"),
                    rs.getDouble("precioVenta"),
                    rs.getString("categoria"),
                    rs.getInt("stock")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizarProducto(Producto p) {
        String sql = "UPDATE Producto SET nombreComercial = ?, precioCosto = ?, precioVenta = ?, categoria = ?, stock = ? WHERE codigoBarras = ?";
        try (PreparedStatement stmt = ConnectionDb.getInstancia().getConexion().prepareStatement(sql)) {
            stmt.setString(1, p.getNombreComercial());
            stmt.setDouble(2, p.getPrecioCosto());
            stmt.setDouble(3, p.getPrecioVenta());
            stmt.setString(4, p.getCategoria());
            stmt.setInt(5, p.getStock());
            stmt.setString(6, p.getCodigoBarras());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(String codigoBarras) {
        String sql = "DELETE FROM Producto WHERE codigoBarras = ?";
        try (PreparedStatement stmt = ConnectionDb.getInstancia().getConexion().prepareStatement(sql)) {
            stmt.setString(1, codigoBarras);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}