package com.example.demo.models.api;

public class CrosswordCellViewModel {
    private Character letter;
    private Integer clueLabel;
    private int xPos;
    private int yPos;

    public CrosswordCellViewModel(){

    }

    public CrosswordCellViewModel(Character letter, Integer clueLabel, int xPos, int yPos) {
        this.letter = letter;
        this.clueLabel = clueLabel;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public Integer getClueLabel() {
        return clueLabel;
    }

    public void setClueLabel(Integer clueLabel) {
        this.clueLabel = clueLabel;
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
}
