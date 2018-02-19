package ru.appavlov.food.web.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.appavlov.food.web.service.model.Author;
import ru.appavlov.food.web.service.repository.AuthorRepository;

@Controller
@RequestMapping(path = "/author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Author> findAll() {
        return authorRepository.findAll();
    }
}