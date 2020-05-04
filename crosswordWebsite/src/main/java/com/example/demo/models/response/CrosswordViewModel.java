package com.example.demo.models.response;

import com.example.demo.models.ClueDirection;
import com.example.demo.models.CluePosition;
import com.example.demo.models.Crossword;
import com.example.demo.models.api.ClueHintViewModel;
import com.example.demo.models.api.CrosswordCellViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CrosswordViewModel {
    private int height;
    private int width;
    private List<CrosswordCellViewModel> cells;
    private List<ClueHintViewModel> hintsAcross;
    private List<ClueHintViewModel> hintsDown;

    public CrosswordViewModel(){

    }

    public CrosswordViewModel(Crossword crossword){
        this.height = crossword.getHeight();
        this.width = crossword.getWidth();
        var cells = new ArrayList<CrosswordCellViewModel>();
        var firstLetters = new ArrayList<CrosswordCellViewModel>();
        var hintsAcross = new ArrayList<ClueHintViewModel>();
        var hintsDown = new ArrayList<ClueHintViewModel>();

        for (CluePosition clue: crossword.getClues()){
            var xPos = clue.getxPos();
            var yPos = clue.getyPos();
            var direction = clue.getClueDirection();
            var isFirstLetter = true;
            for (Character letterOfClue: clue.getClue().getAnswer().toCharArray()){
                var cell = findOrCreateCell(cells, letterOfClue, xPos, yPos);
                if (isFirstLetter){
                    firstLetters.add(cell);
                    if(direction == ClueDirection.HORIZONTAL){
                        hintsAcross.add(new ClueHintViewModel(cell, clue.getClue().getHint()));
                    } else {
                        hintsDown.add(new ClueHintViewModel(cell, clue.getClue().getHint()));
                    }
                    isFirstLetter = false;
                }
                cells.add(cell);
                if(direction == ClueDirection.HORIZONTAL){
                    xPos++;
                } else {
                    yPos++;
                }
            }
            fillInClueLabels(firstLetters);
            sortHints(hintsAcross);
            sortHints(hintsDown);
        }
        this.cells = cells;
        this.hintsAcross = hintsAcross;
        this.hintsDown = hintsDown;
    }

    private CrosswordCellViewModel findOrCreateCell(List<CrosswordCellViewModel> cells, Character letter,
                                                    int xPos, int yPos){
        var cell = cells.stream().filter(c -> (c.getxPos() == xPos) && (c.getyPos() == yPos)).findAny();
        return cell.orElseGet(() -> new CrosswordCellViewModel(letter, null, xPos, yPos));
    }

    private void fillInClueLabels(ArrayList<CrosswordCellViewModel> firstLetterCells){
        sortFirstLetterCells(firstLetterCells);
        var clueLabel = 1;
        var xPos = firstLetterCells.get(0).getxPos();
        var yPos = firstLetterCells.get(0).getyPos();
        for(CrosswordCellViewModel firstLetterCell: firstLetterCells){
            if(firstLetterCell.getxPos() != xPos
                    || firstLetterCell.getyPos() != yPos){
                clueLabel++;
            }
            firstLetterCell.setClueLabel(clueLabel);
        }
    }

    private void sortFirstLetterCells(ArrayList<CrosswordCellViewModel> firstLetterCells){
        var cellComparator = Comparator.comparingInt(CrosswordCellViewModel::getyPos)
                        .thenComparingInt(CrosswordCellViewModel::getxPos);
        firstLetterCells.sort(cellComparator);
    }

    private void sortHints(ArrayList<ClueHintViewModel> hints){
        var hintComparator = Comparator.comparingInt(ClueHintViewModel::getClueLabel);
        hints.sort(hintComparator);
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

    public List<CrosswordCellViewModel> getCells() {
        return cells;
    }

    public void setGrid(List<CrosswordCellViewModel> cells) {
        this.cells = cells;
    }
}
