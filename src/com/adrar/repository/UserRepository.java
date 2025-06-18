package com.adrar.repository;

import com.adrar.model.User;
import com.adrar.utils.BDD;

import java.sql.*;

public class UserRepository {
    /*---------------------------------------
                  Attributs
    ---------------------------------------*/
    private Connection connection = BDD.getConnection();

    /*---------------------------------------
                  Méthodes
    ---------------------------------------*/
    //Méthode pour ajouter un User en BDD
    public User add(User newUser) {
        try {
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
        catch (SQLException e) {
            newUser = null;
        }

        return newUser;
    }

    //Méthode pour récupérer un objet User en BDD
    public User findByEmail(String email) {
        User user = null;
        try {
            //Ecrire la requête
            String request = "SELECT u.id, u.firstname, u.lastname, u.email FROM users AS u WHERE u.email = ?";
            //préparer la requête
            PreparedStatement prepare = connection.prepareStatement(request);
            //Assigner les paramètres
            prepare.setString(1, email);
            //Exécuter la requête
            ResultSet rs = prepare.executeQuery();
            //Test si la requête retourne une ou plusieurs enregistrements
            while(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
            }
            //retourner le résultat
        } catch (SQLException e) {
            user = null;
        }
        return user;
    }
}
