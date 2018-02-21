package ru.appavlov.bookshelf.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.appavlov.bookshelf.model.Book;
import ru.appavlov.bookshelf.repository.BookRepository;

import java.util.List;

@Log
@Controller
@RequestMapping(path = "/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(path = "/all")
    public @ResponseBody
    List<Book> findAll() {
        Page<Book> allWithoutContent =
                bookRepository.findAllWithoutContentAndImage(
                        new PageRequest(0, 10, new Sort(Direction.ASC, "name"))
                );
        return allWithoutContent.getContent();
    }
}