package com.adrar.service;

import com.adrar.model.User;
import com.adrar.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public User addUser(User user) {
        //1 Véfifier si les champs sont remplis
        if(user.getFirstname() != "" && user.getLastname() != ""
                && user.getEmail() != "" && user.getPassword() != ""
                && user.getPassword() != "") {
            //2 vérifier si le compte n'existe pas
            if(userRepository.findByEmail(user.getEmail()) == null) {
                //3 ajouter le compte
                userRepository.add(user);
                System.out.println("Le compte a été ajouté en BDD");
            }
            else {
                System.out.println("Le compte existe déja");
                user = null;
            }
        }
        else {
            System.out.println("Les champs ne sont pas valides");
            user = null;
        }
        
        return user;
    }

}
