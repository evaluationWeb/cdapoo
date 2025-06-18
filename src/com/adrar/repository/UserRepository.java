package com.adrar.repository;

import com.adrar.model.User;
import com.adrar.utils.BDD;

import java.sql.*;
import java.util.ArrayList;

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

    //Méthode pour récupérer un objet User par son ID
    public User find(int id) {
        User user = null;
        try {
            //1 Requête SQL
            String request = "SELECT u.id, u.firstname, u.lastname, u.email FROM users AS u WHERE u.id = ?";
            //2 Préparation de la requête
            PreparedStatement prepare = connection.prepareStatement(request);
            //3 Assigner les paramètres
            prepare.setInt(1, id);
            //4 exécuter la requête
            ResultSet rs = prepare.executeQuery();
            //Créer l'objet User
            while(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            user = null;
        }
        return user;
    }

    //Méthode pour récupérer tous les comptes User
    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String request = "SELECT u.id, u.firstname, u.lastname, u.email FROM users AS u";
            PreparedStatement prepare = connection.prepareStatement(request);
            ResultSet rs = prepare.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        }
        catch(SQLException e) {

        }
        return users;
    }

    //Méthode pour supprimer un compte par son ID
    public boolean delete(int id) {
        try {
            String request = "DELETE FROM users WHERE id = ?";
            PreparedStatement prepare = connection.prepareStatement(request);
            prepare.setInt(1, id);
            int rowsAffected = prepare.executeUpdate();
            if(rowsAffected > 0) {
                return true;
            }
            return false;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Méthode pour mettre à jour les informations du compte
    public User update(User user, String email) {
            User updateUser = null;
        try  {
            String request = "UPDATE users SET firstname = ?, lastname = ?, email = ? WHERE email = ?";
            PreparedStatement prepare = connection.prepareStatement(request);
            prepare.setString(1, user.getFirstname());
            prepare.setString(2, user.getLastname());
            prepare.setString(3, user.getEmail());
            prepare.setString(4, email);
            int rowsAffected = prepare.executeUpdate();
            if(rowsAffected > 0) {
                updateUser = user;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return updateUser;
    }

    //Méthode pour mettre à jour les informations du compte
    public User updateId(User user) {
        User updateUser = null;
        try  {
            String request = "UPDATE users SET firstname = ?, lastname = ?, email = ? WHERE id = ?";
            PreparedStatement prepare = connection.prepareStatement(request);
            prepare.setString(1, user.getFirstname());
            prepare.setString(2, user.getLastname());
            prepare.setString(3, user.getEmail());
            prepare.setInt(4, user.getId());
            int rowsAffected = prepare.executeUpdate();
            if(rowsAffected > 0) {
                updateUser = user;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return updateUser;
    }
}
