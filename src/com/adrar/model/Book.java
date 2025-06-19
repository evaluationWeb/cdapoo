package com.adrar.model;

import java.util.ArrayList;
import java.util.Optional;

public class Book {
    /*---------------------------------------
                  Attributs
    ---------------------------------------*/
    private Integer id;
    private String title;
    private String description;
    private String author;
    private ArrayList<User> users;

    /*---------------------------------------
                  Constructeurs
    ---------------------------------------*/
    public Book() {
        this.users = new ArrayList<>();
    }

    public Book(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.users = new ArrayList<>();
    }

    /*---------------------------------------
                 Getters et Setters
    ---------------------------------------*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", users=" + users +
                '}';
    }
}
