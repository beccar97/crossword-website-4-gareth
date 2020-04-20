package com.example.demo.models;

import com.example.demo.models.db.Clue;

public class CluePosition {
    private Clue clue;
    private int xPos;
    private int yPos;
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

