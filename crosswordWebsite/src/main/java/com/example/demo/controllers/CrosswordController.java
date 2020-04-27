package com.example.demo.controllers;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.api.AddClueRequest;
import com.example.demo.models.db.Crossword;
import com.example.demo.services.CrosswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="api/crosswords")
public class CrosswordController {
    private final CrosswordService crosswordService;

    @Autowired
    CrosswordController(CrosswordService crosswordService) {
        this.crosswordService = crosswordService;
    }

    @GetMapping("/all")
    public @ResponseBody List<Crossword> getAll(){
        return crosswordService.findAll();
    }

    @GetMapping("/createCrossword/{height}/{width}")
    public @ResponseBody Crossword createCrossword(
            @PathVariable Integer height,
            @PathVariable Integer width
    ) {
        return crosswordService.createCrossword(height, width);
    }

    @PostMapping("/{id}/addClue")
    public @ResponseBody
    Crossword addClue(
            @PathVariable Integer id,
            @RequestBody AddClueRequest clueRequest
    ) {
        var crossword = crosswordService.findCrossword(id);

        if (crossword.isEmpty()) throw new NotFoundException("No such crossword");

        return crosswordService.addClue(crossword.get(), clueRequest);
    }

}
