package com.example.demo.controllers;

import com.example.demo.exception.NotFoundException;
import com.example.demo.models.api.CreateClueRequest;
import com.example.demo.models.db.*;
import com.example.demo.repositories.CluePositionRepository;
import com.example.demo.repositories.ClueRepository;
import com.example.demo.repositories.CrosswordRepository;
import com.example.demo.services.CrosswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/clues")
public class ClueController {
    private final ClueRepository clueRepository;
    private final CrosswordRepository crosswordRepository;
    private final CrosswordService crosswordService;
    private final CluePositionRepository cluePositionRepository;

    @Autowired
    ClueController(ClueRepository clueRepository, CrosswordRepository crosswordRepository, CrosswordService crosswordService, CluePositionRepository cluePositionRepository) {
        this.clueRepository = clueRepository;
        this.crosswordRepository = crosswordRepository;
        this.crosswordService = crosswordService;
        this.cluePositionRepository = cluePositionRepository;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Clue> getAllClues() {
        return clueRepository.findAll();
    }

    @PostMapping
    public @ResponseBody
    Clue addClue(@ModelAttribute CreateClueRequest clue) {
        var clue2 = new Clue();
        clue2.setAnswer(clue.getAnswer());
        clue2.setHint(clue.getHint());
        clue2.setDifficulty(Difficulty.MEDIUM);
        return clueRepository.save(clue2);
    }

    @GetMapping(path = "/startingWith/{firstLetter}")
    public @ResponseBody
    List<Clue> findCluesStartingWith(
            @PathVariable String firstLetter
    ) {
        return clueRepository.findAllByAnswerStartingWith(firstLetter);
    }

    @GetMapping(path = "/withLength/{length}")
    public @ResponseBody
    List<Clue> findCluesOfLength(
            @PathVariable Integer length
    ) {
        var clues = (List<Clue>) clueRepository.findAll();
        return clues.stream().filter(clue -> clue.getAnswer().length() == length).collect(Collectors.toList());
    }

    @GetMapping(path = "/matching/{pattern}")
    public @ResponseBody
    List<Clue> findCluesMatching(
            @PathVariable String pattern
    ) {
        return clueRepository.findAllByAnswerLike(pattern);
    }

    @GetMapping(path = "/forPosition/")
    public @ResponseBody
    List<Clue> findCluesForPosition(
            @RequestBody Integer crosswordId,
            @RequestBody Integer xPos,
            @RequestBody Integer yPos,
            @RequestBody ClueDirection direction,
            @RequestBody Integer length
    ) {
        var crosswordOptional = crosswordRepository.findById(crosswordId);

        if (crosswordOptional.isEmpty()) throw new NotFoundException("No such crossword");
        Crossword crossword = crosswordOptional.get();

        if (length == null)
            length = (direction == ClueDirection.HORIZONTAL) ? crossword.getWidth() - xPos : crossword.getHeight() - yPos;

        StringBuilder pattern = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int curXPos = xPos + i;
            int curYPos = yPos + i;

            var charAtPos = crosswordService.findCellValue(crossword, curXPos, curYPos);

            if (charAtPos.isPresent()) {
                pattern.append(charAtPos.get());
            } else pattern.append("_");

        }

        var cluesMatchingPattern = clueRepository.findAllByAnswerLike(pattern.toString());

        return cluesMatchingPattern
                .stream()
                .map(clue -> {
                    var cluePos = new CluePosition();
                    cluePos.setClue(clue);
                    cluePos.setClueDirection(direction);
                    cluePos.setxPos(xPos);
                    cluePos.setyPos(yPos);
                    return cluePositionRepository.save(cluePos);
                })
                .filter(cluePosition -> {
                    // fits in crossword
                })
                .collect(Collectors.toList());
    }
}
