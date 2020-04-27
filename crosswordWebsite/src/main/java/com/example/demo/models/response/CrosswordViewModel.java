package com.example.demo.models.response;

import com.example.demo.models.ClueDirection;
import com.example.demo.models.CluePosition;
import com.example.demo.models.Crossword;

public class CrosswordViewModel {
    private int height;
    private int width;
    private Character[][] grid;

    public CrosswordViewModel(){

    }

    public CrosswordViewModel(Crossword crossword){
        this.height = crossword.getHeight();
        this.width = crossword.getWidth();
        this.grid = new Character[crossword.getWidth()][crossword.getHeight()];

        for (CluePosition clue: crossword.getClues()){
            var xPos = clue.getxPos();
            var yPos = clue.getyPos();
            var direction = clue.getClueDirection();
            for (Character letterOfClue: clue.getClue().getAnswer().toCharArray()){
                grid[xPos][yPos] = letterOfClue;
                if(direction == ClueDirection.HORIZONTAL){
                    xPos++;
                } else {
                    yPos++;
                }
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Character[][] getGrid() {
        return grid;
    }

    public void setGrid(Character[][] grid) {
        this.grid = grid;
    }
}
