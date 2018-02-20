package ru.appavlov.bookshelf.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.appavlov.bookshelf.repository.AuthorRepository;

@Log
@Controller
@RequestMapping(path = "/author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping(path = "/all")
    public @ResponseBody
    String findAll() {
        authorRepository.findAll();
        return "author";
    }
}