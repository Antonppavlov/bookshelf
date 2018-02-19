package ru.appavlov.food.web.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.appavlov.food.web.service.model.Author;
import ru.appavlov.food.web.service.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Iterable<Author> getUserDao() {
        //предполагается что здесь логика проверок и тд
        return authorRepository.findAll();
    }
}
