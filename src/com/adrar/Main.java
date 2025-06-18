package com.adrar;

import com.adrar.model.User;
import com.adrar.service.UserService;

public class Main {
    public static void main(String[] args) {
        User mathieu = new User("Mathieu", "Mithridate", "mathieu5588@test.com", "1234");
        UserService userService = new UserService();
        User addUser = userService.addUser(mathieu);
    }
}
