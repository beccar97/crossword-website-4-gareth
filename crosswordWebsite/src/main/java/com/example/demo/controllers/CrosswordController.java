package com.example.demo.controllers;

import com.example.demo.models.Crossword;
import com.example.demo.models.db.CluePosition;
import com.example.demo.services.CrosswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="api/crosswords")
public class CrosswordController {
    private final CrosswordService crosswordService;

    @Autowired
    CrosswordController(CrosswordService crosswordService) {
        this.crosswordService = crosswordService;
    }

    @PostMapping("/createCrossword/{height}/{width}")
    public @ResponseBody Crossword createCrossword(
            @PathVariable Integer height,
            @PathVariable Integer width
    ) {
        return crosswordService.createCrossword(height, width);
    }

    @PostMapping("/{id}/addClue")
    public @ResponseBody
    Crossword addClue(
            @ModelAttribute CluePosition cluePosition,
            @PathVariable Integer id
    ) {
        var crossword = crosswordService.findCrossword(id);

        if (crossword.isEmpty()) throw new Error("No such crossword");

        var updatedCrossword = crosswordService.addClue(crossword.get(), cluePosition);

        if (updatedCrossword == null) throw new Error("Clue does not fit in this position");
        else return updatedCrossword;
    }

}
