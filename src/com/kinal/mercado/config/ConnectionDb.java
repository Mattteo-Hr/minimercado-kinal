package com.kinal.mercado.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
    private static ConnectionDb instancia;
    private Connection conexion;

    private ConnectionDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(CredentialsDb.URL, CredentialsDb.USER, CredentialsDb.PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionDb getInstancia() {
        if (instancia == null) {
            instancia = new ConnectionDb();
        }
        return instancia;
    }

    public Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(CredentialsDb.URL, CredentialsDb.USER, CredentialsDb.PASS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }
}