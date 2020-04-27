package com.example.demo.models;

import com.example.demo.models.db.Clue;

import java.util.ArrayList;
import java.util.List;

public class Crossword {
    private int height;
    private int width;
    private List<CluePosition> cluePositions;

    public Crossword(int height, int width){
        this.height = height;
        this.width = width;
        this.cluePositions = new ArrayList<>();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<CluePosition> getClues() {
        return cluePositions;
    }

    public boolean addClue(CluePosition clue){
        if (!withinBounds(clue)) return false;
        for (CluePosition existingClue : cluePositions){
            if (!compatibleClues(clue, existingClue)) return false;
        }

        cluePositions.add(clue);
        return true;
    }

    private boolean withinBounds(CluePosition clue){
        if(clue.getClueDirection() == ClueDirection.HORIZONTAL){
            return (clue.getxPos() + clue.getClue().getAnswer().length() <= width);
        } else {
            return (clue.getyPos() + clue.getClue().getAnswer().length() <= height);
        }
    }

    private static boolean compatibleClues(CluePosition clue1, CluePosition clue2){
        if(clue1.getClueDirection() != clue2.getClueDirection()){
            return validateIntersection(clue1, clue2);
        } else if (clue1.getClueDirection() == ClueDirection.HORIZONTAL) {
           return validateHorizontalPair(clue1, clue2);
        } else return validateVerticalPair(clue1, clue2);
    }

    private static boolean validateHorizontalPair(CluePosition clue1, CluePosition clue2) {
        if (clue1.getyPos() != clue2.getyPos()) return true;

        var clue1Start = clue1.getxPos();
        var clue1End = clue1.getxPos() + clue1.getClue().getAnswer().length() - 1;
        var clue2Start = clue2.getxPos();
        var clue2End = clue2.getxPos() + clue2.getClue().getAnswer().length() - 1;

        if (clue1End < clue2Start - 1) return true;
        else if (clue1Start > clue2End + 1) return true;
        else return false;
    }

    private static boolean validateVerticalPair(CluePosition clue1, CluePosition clue2) {
        if (clue1.getxPos() != clue2.getxPos()) return true;

        var clue1Start = clue1.getyPos();
        var clue1End = clue1.getyPos() + clue1.getClue().getAnswer().length() - 1;
        var clue2Start = clue2.getyPos();
        var clue2End = clue2.getyPos() + clue2.getClue().getAnswer().length() - 1;

        if (clue1End < clue2Start - 1) return true;
        else if (clue1Start > clue2End + 1) return true;
        else return false;
    }

    private static boolean validateIntersection(CluePosition clue1, CluePosition clue2){
        var horizontalClue = (clue1.getClueDirection() == ClueDirection.HORIZONTAL) ? clue1 : clue2;
        var verticalClue = (clue1.getClueDirection() == ClueDirection.VERTICAL) ? clue1 : clue2;
        var horizontalAnswerChars = horizontalClue.getClue().getAnswer().toCharArray();
        var verticalAnswerChars = verticalClue.getClue().getAnswer().toCharArray();

        var x = verticalClue.getxPos();
        var y = horizontalClue.getyPos();

        if (x == horizontalClue.getxPos() - 1 && inVerticalRange(horizontalClue, verticalClue)) return false;
        if (x == horizontalClue.getxPos() + horizontalAnswerChars.length && inVerticalRange(horizontalClue, verticalClue)) return false;

        if (y == verticalClue.getyPos() - 1 && inHorizontalRange(horizontalClue, verticalClue)) return false;
        if (y == verticalClue.getyPos() + verticalAnswerChars.length && inHorizontalRange(horizontalClue, verticalClue)) return false;

        if (!inVerticalRange(horizontalClue, verticalClue) && !inHorizontalRange(horizontalClue, verticalClue)) return true;

        var horizontalIntersectChar = horizontalAnswerChars[x - horizontalClue.getxPos()];
        var verticalIntersectChar = verticalAnswerChars[y - verticalClue.getyPos()];

        return horizontalIntersectChar == verticalIntersectChar;

    }

    private static boolean inVerticalRange(CluePosition horizontalClue, CluePosition verticalClue) {
        var horizontalY = horizontalClue.getyPos();
        var verticalStart = verticalClue.getyPos();
        var verticalEnd = verticalClue.getyPos() + verticalClue.getClue().getAnswer().length() - 1;

        return (verticalStart <= horizontalY && horizontalY <= verticalEnd);
    }

    private static boolean inHorizontalRange(CluePosition horizontalClue, CluePosition verticalClue) {
        var verticalX = verticalClue.getxPos();
        var horizontalStart = horizontalClue.getxPos();
        var horizontalEnd = horizontalClue.getxPos() + horizontalClue.getClue().getAnswer().length() - 1;

        return (horizontalStart <= verticalX && verticalX <= horizontalEnd);
    }

    public boolean validate() {
        var numClues = cluePositions.size();
        for (int i = 0; i < numClues; i++) {
            for (int j = i + 1; j < numClues; j++) {
                var clue1 = cluePositions.get(i);
                var clue2 = cluePositions.get(j);
                if (compatibleClues(clue1, clue2)) return false;
            }
        }
        return true;
    }
}
