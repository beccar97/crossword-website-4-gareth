package com.example.demo.controllers;

import com.example.demo.models.response.CrosswordViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class NewCrosswordPageController {

    @GetMapping(path="/newCrosswordPage")
    public String newCrosswordPage(@RequestBody CrosswordViewModel crossword, Model model) {
        model.addAttribute("cells", crossword.getCells());
        model.addAttribute("height", crossword.getHeight());
        model.addAttribute("width", crossword.getWidth());
        return "newCrosswordPage";
    }
}
