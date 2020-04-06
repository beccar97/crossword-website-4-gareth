package com.example.demo.models.db;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @NotNull
    @Column
    private String name;
}
