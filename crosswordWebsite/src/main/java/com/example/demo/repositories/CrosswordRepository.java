package com.example.demo.repositories;

import com.example.demo.models.db.Crossword;
import org.springframework.data.repository.CrudRepository;

public interface CrosswordRepository extends CrudRepository<Crossword, Integer> {

}
