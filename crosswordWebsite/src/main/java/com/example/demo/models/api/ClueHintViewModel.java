package com.example.demo.models.api;

public class ClueHintViewModel {
    private CrosswordCellViewModel firstLetterCell;
    private String hint;

    public ClueHintViewModel(){

    }

    public ClueHintViewModel(CrosswordCellViewModel firstLetterCell, String hint) {
        this.firstLetterCell = firstLetterCell;
        this.hint = hint;
    }

    public Integer getClueLabel(){
        return firstLetterCell.getClueLabel();
    }

    public CrosswordCellViewModel getFirstLetterCell() {
        return firstLetterCell;
    }

    public void setFirstLetterCell(CrosswordCellViewModel firstLetterCell) {
        this.firstLetterCell = firstLetterCell;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
