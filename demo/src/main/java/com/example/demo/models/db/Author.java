package com.example.demo.models.db;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String username;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
