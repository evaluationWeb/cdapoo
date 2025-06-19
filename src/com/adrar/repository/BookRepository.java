package com.adrar.repository;

import com.adrar.model.Book;
import com.adrar.utils.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository {
    /*---------------------------------------
                  Attributs
    ---------------------------------------*/
    private Connection connection = BDD.getConnection();

    public Book add(Book book) {
        Book newBook = null;
        try {
            //1 écrire la requête
            String request = "INSERT INTO book(title, description, author) VALUE(?,?,?)";
            //2 préparer
            PreparedStatement prepare = connection.prepareStatement(request);
            //3 assigner les paramètres
            prepare.setString(1, book.getTitle());
            prepare.setString(2, book.getDescription());
            prepare.setString(3, book.getAuthor());
            //4 exécuter la requête
            int rows = prepare.executeUpdate();
            if(rows > 0) {
                newBook = book;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return newBook;
    }

    public Book find(int id) {
        Book book = null;
        try {
            //1 écrire la requête
            String request = "SELECT b.id, b.title, b.description, b.author FROM book AS b WHERE b.id = ?";
            //2 préparer la requête
            PreparedStatement prepare = connection.prepareStatement(request);
            //3 assigner les paramètres
            prepare.setInt(1, id);
            //4 exécuter la requête
            ResultSet rs = prepare.executeQuery();
            //Récupérer le resultat de la requête
            while (rs.next()) {
                book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDescription(rs.getString("description"));
                book.setAuthor(rs.getString("author"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public boolean delete(int id) {
        boolean result = false;
        try {
            String request = "DELETE FROM book as b WHERE b.id = ?";
            PreparedStatement prepare = connection.prepareStatement(request);
            prepare.setInt(1, id);
            int rows = prepare.executeUpdate();
            if(rows == 1) {
                result = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
