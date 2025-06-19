package com.adrar;

import com.adrar.model.User;
import com.adrar.repository.BookRepository;
import com.adrar.repository.UserRepository;
import com.adrar.service.BookService;
import com.adrar.service.UserService;

public class Main {
    public static void main(String[] args) {
        BookService repo = new BookService();

        System.out.println("Sans les utilisateurs");
        System.out.println(repo.getAllBooks());
        System.out.println("Avec les utilisateurs");
        System.out.println(repo.getAllBooksWithUser());
    }
}
