package com.example.demo.models.db;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Crossword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @NotNull
    @Column
    private int height;

    @NotNull
    @Column
    private int width;

    @OneToMany
    private List<CluePosition> cluePositions;

    public Integer getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<CluePosition> getCluePositions() {
        return cluePositions;
    }

    public void setCluePositions(List<CluePosition> cluePositions) {
        this.cluePositions = cluePositions;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
