package com.adrar.service;

import com.adrar.model.User;
import com.adrar.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public User addUser(User user) {
        //1 Vérifier si les champs sont remplis
        if(!user.getFirstname().isEmpty() && !user.getLastname().isEmpty()
                && !user.getEmail().isEmpty() && !user.getPassword().isEmpty()) {
            //2 vérifier si le compte n'existe pas
            if(userRepository.findByEmail(user.getEmail()) == null) {
                //3 ajouter le compte
                System.out.println("Le compte a été ajouté en BDD");
                userRepository.add(user);
            }
            //Test le compte existe déja
            else {
                System.out.println("Le compte existe déja");
                user = null;
            }
        }
        //Test les champs ne sont pas corrects
        else {
            System.out.println("Les champs ne sont pas valides");
            user = null;
        }
        return user;
    }

}
