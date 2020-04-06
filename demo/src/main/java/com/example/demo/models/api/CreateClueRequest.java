package com.example.demo.models.api;

import com.sun.istack.NotNull;

public class CreateClueRequest {
    @NotNull
    private String answer;
    @NotNull
    private String hint;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
