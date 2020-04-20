package com.example.demo.controllers;

import com.example.demo.models.api.CreateClueRequest;
import com.example.demo.models.db.Clue;
import com.example.demo.models.db.Difficulty;
import com.example.demo.repositories.ClueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api/clues")
public class ClueController {
    @Autowired
    private final ClueRepository clueRepository;

    ClueController(ClueRepository clueRepository){
        this.clueRepository = clueRepository;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Clue> getAllClues() {
        return clueRepository.findAll();
    }

    @PostMapping
    public @ResponseBody Clue addClue(@ModelAttribute CreateClueRequest clue) {
        var clue2 = new Clue();
        clue2.setAnswer(clue.getAnswer());
        clue2.setHint(clue.getHint());
        clue2.setDifficulty(Difficulty.MEDIUM);
        return clueRepository.save(clue2);
    }
}
