package com.example.demo.models.db;

public enum Difficulty {
    VERYEASY("Very Easy"),
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    VERYHARD("Very Hard");

    private final String name;

    Difficulty(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
