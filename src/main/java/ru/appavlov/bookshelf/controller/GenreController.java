package ru.appavlov.bookshelf.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.appavlov.bookshelf.model.Genre;
import ru.appavlov.bookshelf.service.impl.GenreService;

import java.util.List;

@Log
@RestController
@RequestMapping(path = "/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    // получить все записи без сортировки (сортировку уже могут сами выбирать на стороне клиента)
    @GetMapping(value = "/all")
    public List<Genre> getGenres() {
        return genreService.getAll();
    }

    // возвращает всезаписи с постраничностью
    @GetMapping(value = "/allPage")
    public List<Genre> allPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return genreService.getAll(pageNumber, pageSize, "fio", Sort.Direction.ASC).getContent();
    }

    // поиск записей без постраничности (сразу весь список)
    @GetMapping(value = "/search")
    public List<Genre> search(@RequestParam("name") String name) {
        return genreService.search(name);
    }

    // поиск записей с постраничностью
    @GetMapping(value = "/searchPage")
    public List<Genre> searchPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam("fio") String fio) {
        return genreService.search(pageNumber, pageSize, "fio", Sort.Direction.ASC, fio).getContent(); // т.к. возвращается объект Page, надо у него вызвать getContent, чтобы получить коллекцию
    }

    // получение объекта по id
    @GetMapping
    public Genre get(@RequestParam("id") long id) {
        return genreService.get(id);
    }

    // удаление объекта по id
    @DeleteMapping
    public boolean delete(@RequestParam("id") long id) {
        genreService.delete(genreService.get(id));// сначала получаем объект по id, потом его удаляем
        return true;
    }

    // добавление переданного объекта
    @PostMapping
    public boolean add(@RequestBody Genre genre) {
        genreService.save(genre);
        return true;
    }
}
