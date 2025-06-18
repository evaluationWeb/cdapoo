package com.adrar;

import com.adrar.model.User;
import com.adrar.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
       User mathieu = new User("Mathieu", "Mithridate", "mathieu@test.com", "1234");
        System.out.println(UserRepository.findByEmail(mathieu.getEmail()));
       /*if(UserRepository.add(mathieu) != null) {
           System.out.println("Le compte "+ mathieu.getEmail() + " a été ajouté");
       }
       else{
           System.out.println("L'enregistrement à échoué");
       }*/
    }
}
