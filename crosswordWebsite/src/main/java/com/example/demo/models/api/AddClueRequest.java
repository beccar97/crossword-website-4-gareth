package com.example.demo.models.api;

import com.example.demo.models.db.ClueDirection;
import com.sun.istack.NotNull;

public class AddClueRequest {
    @NotNull
    private Integer clueId;

    @NotNull
    private int xPos;

    @NotNull
    private int yPos;

    @NotNull
    private ClueDirection clueDirection;

    public Integer getClueId() {
        return clueId;
    }

    public void setClueId(Integer clueId) {
        this.clueId = clueId;
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
