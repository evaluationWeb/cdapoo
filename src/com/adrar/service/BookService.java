package com.adrar.service;

import com.adrar.model.Book;
import com.adrar.repository.BookRepository;

import java.util.ArrayList;

public class BookService {
    /*---------------------------------------
                  Attributs
    ---------------------------------------*/
    private BookRepository bookRepository;
    /*---------------------------------------
                      Constructeurs
    ---------------------------------------*/
    public BookService() {
        this.bookRepository = new BookRepository();
    }

    //Méthode pour ajouter un livre en BDD
    public Book addBook(Book book) {
        Book newBook = null;
        //tester si les champs sont tous renseignés
        if(!book.getTitle().isEmpty() && !book.getDescription().isEmpty() && !book.getAuthor().isEmpty()) {
            //tester si le livre n'existe pas déja
            if(bookRepository.findBookByTitle(book.getTitle()).isEmpty()) {
                newBook  = bookRepository.add(book);
                System.out.println("Le livre : " + book.getTitle() + " a été ajouté" );
            }
            //Test le livre existe déja
            else {
                System.out.println("Le livre : " + book.getTitle() + " existe déja");
            }
        }
        //Test les champs ne sont pas tous remplis
        else {
            System.out.println("Les champs ne sont pas tous remplis");
        }
        return newBook;
    }

    //Méthode qui retourne tous les livres de la BDD
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = bookRepository.findAll();
        if(books.isEmpty()) {
            System.out.println("Le livre est vide");
        }
        return books;
    }

    //Méthode
}
