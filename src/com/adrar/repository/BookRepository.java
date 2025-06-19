package com.adrar.repository;

import com.adrar.model.Book;
import com.adrar.utils.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
