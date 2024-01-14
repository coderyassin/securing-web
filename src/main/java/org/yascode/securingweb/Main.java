package org.yascode.securingweb;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String rawPassword = "user";
        String encodedPassword = "$2a$10$bEl9KVWdqceWToRWawJ2xuy5maYiUsJbriCu0PYdnRWuDMs0ouTNa";
        boolean matches = encoder.matches(rawPassword, encodedPassword);
        System.out.println(matches);
    }

}
