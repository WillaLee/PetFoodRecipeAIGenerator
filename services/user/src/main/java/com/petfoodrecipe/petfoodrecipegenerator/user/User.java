package com.petfoodrecipe.petfoodrecipegenerator.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name = "user_details")
public class User {
    @Size(min=2, message = "Name should have at least 2 characters")
    private String name;
    @Id
    @GeneratedValue
    private int id;

    public User() {

    }

    public @Size(min = 2, message = "Name should have at least 2 characters") String getName() {
        return name;
    }

    public void setName(@Size(min = 2, message = "Name should have at least 2 characters") String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
