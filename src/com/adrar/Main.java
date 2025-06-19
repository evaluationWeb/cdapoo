package com.adrar;

import com.adrar.model.User;
import com.adrar.repository.BookRepository;
import com.adrar.repository.UserRepository;
import com.adrar.service.UserService;

public class Main {
    public static void main(String[] args) {
        BookRepository repo = new BookRepository();
        System.out.println(repo.findAllWithUser());;
    }
}
