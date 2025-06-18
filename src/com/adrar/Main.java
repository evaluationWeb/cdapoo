package com.adrar;

import java.sql.Connection;
import com.adrar.utils.BDD;
public class Main {
    public static void main(String[] args) {
       Connection mysql = BDD.getConnection();
    }
}
