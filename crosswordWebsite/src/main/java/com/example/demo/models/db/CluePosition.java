package com.example.demo.models.db;

import com.example.demo.models.Crossword;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table
public class CluePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @ManyToOne
    private Clue clue;

    @NotNull
    @Column
    private int xPos;

    @NotNull
    @Column
    private int yPos;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private ClueDirection clueDirection;

    public Clue getClue() {
        return clue;
    }

    public void setClue(Clue clue) {
        this.clue = clue;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public ClueDirection getClueDirection() {
        return clueDirection;
    }

    public void setClueDirection(ClueDirection clueDirection) {
        this.clueDirection = clueDirection;
    }
}

