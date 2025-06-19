package com.adrar.repository;

import com.adrar.model.Book;
import com.adrar.model.User;
import com.adrar.utils.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookRepository {
    /*---------------------------------------
                  Attributs
    ---------------------------------------*/
    private Connection connection = BDD.getConnection();

    //Méthode pour ajouter un livre en BDD
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

    //Méthode pour récupérer un objet livre depuis son ID en BDD
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

    //Méthode pour supprimer un objet livre depuis son ID en BDD
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

    //Méthode pour mettre à jour un objet Livre en BDD
    public Book update(Book book) {
        Book newBook = null;
        try{
            String request = "UPDATE book AS b SET b.title = ?, b.description = ?, b.author = ? WHERE b.id = ?";
            PreparedStatement prepare = connection.prepareStatement(request);
            prepare.setString(1, book.getTitle());
            prepare.setString(2, book.getDescription());
            prepare.setString(3, book.getAuthor());
            prepare.setInt(4, book.getId());

            if(prepare.executeUpdate() > 0) {
                newBook = book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newBook;
    }
    //Méthode pour récupérer la liste des livres en BDD
    public ArrayList<Book> findAll() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String request = "SELECT b.id, b.title, b.description, b.author FROM book as b";
            PreparedStatement prepare = connection.prepareStatement(request);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDescription(rs.getString("description"));
                book.setAuthor(rs.getString("author"));
                books.add(book);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    //Méthode pour récupérer la liste des livres avec leur User en BDD
    public ArrayList<Book> findAllWithUser() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String request = "SELECT b.id, b.title, b.description, b.author, " +
                    " GROUP_CONCAT(u.id) AS users  FROM book as b"
                    +" INNER JOIN users_book as ub ON b.id = ub.id_book" +
                    " INNER JOIN users as u ON ub.id_users = u.id" +
                    " GROUP BY b.id";
            PreparedStatement prepare = connection.prepareStatement(request);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDescription(rs.getString("description"));
                book.setAuthor(rs.getString("author"));
                //Récupérer la liste des users (ID)
                String idUsers = rs.getString("users");
                //Convertie en Tableau
                String[] ids = idUsers.split(",");
                //Parcourir la liste ID
                for(String id : ids) {
                    //Créer un objet User
                    User user = new User();
                    user.setId(Integer.parseInt(id));
                    book.addUser(user);
                }
                books.add(book);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    //Méthode pour récupérer des livres depuis leur titre en BDD
    public ArrayList<Book> findBookByTitle(String title) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String request = "SELECT b.id, b.title, b.description, b.author FROM book as b " +
                    "WHERE b.title = ?";
            PreparedStatement prepare = connection.prepareStatement(request);
            prepare.setString(1, title);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDescription(rs.getString("description"));
                book.setAuthor(rs.getString("author"));
                books.add(book);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    //Méthode pour récupérer des livres depuis leur auteur en BDD
    public ArrayList<Book> findBookByAuthor(String author) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            String request = "SELECT b.id, b.title, b.description, b.author FROM book as b " +
                    "WHERE b.author = ?";
            PreparedStatement prepare = connection.prepareStatement(request);
            prepare.setString(1, author);
            ResultSet rs = prepare.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDescription(rs.getString("description"));
                book.setAuthor(rs.getString("author"));
                books.add(book);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
