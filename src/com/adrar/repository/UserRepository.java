package com.adrar.repository;

import com.adrar.model.User;
import com.adrar.utils.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
    /*---------------------------------------
                  Attributs
    ---------------------------------------*/
    private static Connection connection = BDD.getConnection();

    /*---------------------------------------
                  Méthodes
    ---------------------------------------*/
    public static User add(User newUser) {
        try {
            //1 se connecter
            Statement stmt = connection.createStatement();
            //2 écrire la requête
            String request = "INSERT INTO " +
                    "users(firstname, lastname, email, password) " +
                    "VALUES (?,?,?,?)";
            //3 préparer la requête (prepare en PHP)
            PreparedStatement prepare = connection.prepareStatement(request);
            //4 assigner les paramètres
            prepare.setString(1, newUser.getFirstname());
            prepare.setString(2, newUser.getLastname());
            prepare.setString(3, newUser.getEmail());
            prepare.setString(4, newUser.getPassword());
            //5 exécuter la requête
            int addRows = prepare.executeUpdate();
            if(addRows < 1) {
                newUser = null;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return newUser;
    }
}
