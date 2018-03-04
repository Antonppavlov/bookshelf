package ru.appavlov.bookshelf.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.appavlov.bookshelf.model.Author;
import ru.appavlov.bookshelf.service.impl.AuthorService;

import java.util.List;

@Log
@RestController
@RequestMapping(path = "/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // получить все записи без сортировки (сортировку уже могут сами выбирать на стороне клиента)
    @RequestMapping("/all")
    public List<Author> getAuthors() {
        return authorService.getAll();
    }

    // возвращает всезаписи с постраничностью
    @RequestMapping("/allPage")
    public List<Author> allPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return authorService.getAll(pageNumber, pageSize, "fio", Sort.Direction.ASC).getContent();
    }

    // поиск записей без постраничности (сразу весь список)
    @RequestMapping("/search")
    public List<Author> search(@RequestParam("fio") String fio) {
        return authorService.search(fio);
    }

    // поиск записей с постраничностью
    @RequestMapping("/searchPage")
    public List<Author> searchPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam("fio") String fio) {
        return authorService.search(pageNumber, pageSize, "fio", Sort.Direction.ASC, fio).getContent();// т.к. возвращается объект Page, надо у него вызвать getContent, чтобы получить коллекцию
    }

    @RequestMapping("/get")
    public Author get(@RequestParam("id") long id) {
        return authorService.get(id);
    }

    @RequestMapping("/delete")
    public boolean delete(@RequestParam("id") long id) {
        authorService.delete(authorService.get(id));// сначала получаем автора по id, потом его удаляем
        return true;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Author author) {
        authorService.save(author);
        return true;
    }

}
