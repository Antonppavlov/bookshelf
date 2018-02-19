package ru.appavlov.bookshelf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.appavlov.bookshelf.model.Author;
import ru.appavlov.bookshelf.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Iterable<Author> getUserDao() {
        //предполагается что здесь логика проверок и тд
        return authorRepository.findAll();
    }
}
