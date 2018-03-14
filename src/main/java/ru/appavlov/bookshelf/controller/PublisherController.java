package ru.appavlov.bookshelf.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.appavlov.bookshelf.model.Publisher;
import ru.appavlov.bookshelf.service.impl.PublisherService;

import java.util.List;

@Log
@RestController
@RequestMapping(path = "/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    // получить все записи без сортировки (сортировку уже могут сами выбирать на стороне клиента)
    @GetMapping(value = "/all")
    public List<Publisher> getPublishers() {
        return publisherService.getAll();
    }

    // возвращает всезаписи с постраничностью
    @GetMapping(value = "/allPage")
    public List<Publisher> allPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return publisherService.getAll(pageNumber, pageSize, "fio", Sort.Direction.ASC).getContent();
    }

    // поиск записей без постраничности (сразу весь список)
    @GetMapping(value = "/search")
    public List<Publisher> search(@RequestParam("name") String name) {
        return publisherService.search(name);
    }

    // поиск записей с постраничностью
    @GetMapping(value = "/searchPage")
    public List<Publisher> searchPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam("fio") String fio) {
        return publisherService.search(pageNumber, pageSize, "fio", Sort.Direction.ASC, fio).getContent(); // т.к. возвращается объект Page, надо у него вызвать getContent, чтобы получить коллекцию
    }

    // получение объекта по id
    @GetMapping
    public Publisher get(@RequestParam("id") long id) {
        return publisherService.get(id);
    }

    // удаление объекта по id
    @DeleteMapping
    public boolean delete(@RequestParam("id") long id) {
        publisherService.delete(publisherService.get(id));// сначала получаем объект по id, потом его удаляем
        return true;
    }

    // добавление переданного объекта
    @PostMapping
    public boolean add(@RequestBody Publisher Publisher) {
        publisherService.save(Publisher);
        return true;
    }
}
