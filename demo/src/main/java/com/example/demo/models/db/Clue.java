package com.example.demo.models.db;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table
public class Clue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @NotNull
    @Column
    private String answer;

    @NotNull
    @Column
    private String hint;

    public Integer getId() {
        return id;
    }

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
