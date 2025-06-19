package com.adrar.service;

import com.adrar.model.User;
import com.adrar.repository.UserRepository;
import java.util.ArrayList;

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

    public User getUserById(int id) {
        User user = null;
        //Test si l'id est correct
        if(id > 0) {
            user = userRepository.find(id);
            //Test si user est null
            if(user == null) {
                System.out.println("Le compte n'existe pas");
            }
            //Test sinon le compte existe
            else {
                System.out.println("Le compte existe");
            }
        }
        //Test id invalide
        else {
            System.out.println("L'id n'existe pas");
        }
        return user;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = userRepository.findAll();
        if(users.isEmpty()) {
            System.out.println("Il n'y à pas de compte");
        }
        return users;
    }

    public void deleteUser(int id) {
        if(id < 0) {
            System.out.println("Id invalide");
        }
        else {
            if(userRepository.delete(id)){
                System.out.println("Le compte a été supprimé");
            }
            else {
                System.out.println("Le compte n'existe pas");
            }
        }
    }

    //Méthode qui met à jour l'utilisateur
    public User updateUser(User user) {
        User updatedUser = null;
        //test si l'id est valide
        if(user.getId() > 0) {
            if(!user.getFirstname().isEmpty() && !user.getLastname().isEmpty() && !user.getEmail().isEmpty()) {
                updatedUser = userRepository.find(user.getId());
                //Test si le compte existe
                if(updatedUser.getId() > 0) {
                    //Mettre à jour le compte
                    updatedUser = userRepository.updateId(updatedUser);
                    System.out.println("Le compte a été mis à jour");
                }
                else {
                    System.out.println("Le compte n'existe pas");
                }
            }
            else {
                System.out.println("Les champs ne sont pas tous remplis");
            }
        }
        return updatedUser;
    }

    //méthode qui met à jour un compte avec un email
    public User updateUserByEmail(User user, String email) {
        User updatedUser = null;
        //test si l'email est valide
        if(!email.isEmpty()) {
            //test si les champs sont remplis
            if(!user.getFirstname().isEmpty() && !user.getLastname().isEmpty() && !user.getEmail().isEmpty()) {
                updatedUser = userRepository.findByEmail(email);
                //Test si le compte existe
                if(updatedUser.getId() > 0) {
                    //Mettre à jour le compte
                    updatedUser = userRepository.update(user, email);
                    System.out.println("Le compte a été mis à jour");
                }
                else {
                    System.out.println("Le compte n'existe pas");
                }
            }
            else {
                System.out.println("Les champs ne sont pas tous remplis");
            }
        }
        return updatedUser;
    }
}
