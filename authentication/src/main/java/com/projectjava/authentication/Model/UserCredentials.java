package com.projectjava.authentication.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserCredentials")
public class UserCredentials {
    private String username, password;
    // getters and setters ...


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
