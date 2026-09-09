package com.kinal.mercado.model;

public class Producto {
    private String codigoBarras;
    private String nombreComercial;
    private double precioCosto;
    private double precioVenta;
    private String categoria;
    private int stock;

    public Producto() {
    }

    public Producto(String codigoBarras, String nombreComercial, double precioCosto, double precioVenta, String categoria, int stock) {
        this.codigoBarras = codigoBarras;
        this.nombreComercial = nombreComercial;
        this.precioCosto = precioCosto;
        setPrecioVenta(precioVenta);
        this.categoria = categoria;
        this.stock = stock;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        if (precioVenta < this.precioCosto) {
            throw new IllegalArgumentException("El precio de venta no puede ser menor al precio de costo.");
        }
        this.precioVenta = precioVenta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}