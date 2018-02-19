package ru.appavlov.food.web.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.appavlov.food.web.service.model.Genre;
import ru.appavlov.food.web.service.repository.GenreRepository;

import java.util.List;

@Controller
@RequestMapping(path = "/genre")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping(path = "/all")
    public @ResponseBody
    List<Genre> findAll() {
        return genreRepository.findAll();
    }
}