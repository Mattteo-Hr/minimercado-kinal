package com.kinal.mercado.controller;

import com.kinal.mercado.dao.ProductoDao;
import com.kinal.mercado.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductoController implements Initializable {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCosto;
    @FXML private TextField txtVenta;
    @FXML private TextField txtCategoria;
    @FXML private TextField txtStock;

    @FXML private TableView<Producto> tblProductos;
    @FXML private TableColumn<Producto, String> colCodigo;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colCosto;
    @FXML private TableColumn<Producto, Double> colVenta;
    @FXML private TableColumn<Producto, String> colCategoria;
    @FXML private TableColumn<Producto, Integer> colStock;

    private ProductoDao productoDao = new ProductoDao();
    private ObservableList<Producto> listaProductos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoBarras"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreComercial"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("precioCosto"));
        colVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
        cargarDatos();
    }

    public void cargarDatos() {
        listaProductos = FXCollections.observableArrayList(productoDao.listarProductos());
        tblProductos.setItems(listaProductos);
    }

    @FXML
    public void guardar() {
        try {
            Producto p = new Producto(
                txtCodigo.getText(),
                txtNombre.getText(),
                Double.parseDouble(txtCosto.getText()),
                Double.parseDouble(txtVenta.getText()),
                txtCategoria.getText(),
                Integer.parseInt(txtStock.getText())
            );
            productoDao.agregarProducto(p);
            cargarDatos();
            limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void seleccionarElemento() {
        Producto p = tblProductos.getSelectionModel().getSelectedItem();
        if (p != null) {
            txtCodigo.setText(p.getCodigoBarras());
            txtNombre.setText(p.getNombreComercial());
            txtCosto.setText(String.valueOf(p.getPrecioCosto()));
            txtVenta.setText(String.valueOf(p.getPrecioVenta()));
            txtCategoria.setText(p.getCategoria());
            txtStock.setText(String.valueOf(p.getStock()));
        }
    }

    @FXML
    public void actualizar() {
        try {
            Producto p = new Producto(
                txtCodigo.getText(),
                txtNombre.getText(),
                Double.parseDouble(txtCosto.getText()),
                Double.parseDouble(txtVenta.getText()),
                txtCategoria.getText(),
                Integer.parseInt(txtStock.getText())
            );
            productoDao.actualizarProducto(p);
            cargarDatos();
            limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void eliminar() {
        Producto p = tblProductos.getSelectionModel().getSelectedItem();
        if (p != null) {
            productoDao.eliminarProducto(p.getCodigoBarras());
            cargarDatos();
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtCosto.clear();
        txtVenta.clear();
        txtCategoria.clear();
        txtStock.clear();
    }
}