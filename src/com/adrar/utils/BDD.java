package com.adrar.utils;

import com.adrar.Env;

import java.sql.*;

public class BDD {

    //Connexion à la BDD
    private static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection(Env.BD_URL,Env.BD_USER, Env.BD_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}
