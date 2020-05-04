package com.example.demo.repositories;

import com.example.demo.models.db.Clue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClueRepository extends CrudRepository<Clue, Integer> {
    List<Clue> findAllByAnswerStartingWith(String answerBeginning);
    List<Clue> findAllByAnswerLike(String pattern);
}