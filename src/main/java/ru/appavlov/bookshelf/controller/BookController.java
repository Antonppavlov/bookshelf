package ru.appavlov.bookshelf.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ru.appavlov.bookshelf.model.Book;
import ru.appavlov.bookshelf.service.impl.BookService;

import java.util.List;

@Log
@RestController
@RequestMapping(path = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    // получить все записи без сортировки (сортировку уже могут сами выбирать на стороне клиента)
    @GetMapping(value = "/all")
    public List<Book> getBooks() {
        return bookService.getAll();
    }

    // возвращает всезаписи с постраничностью
    @GetMapping(value = "/allPage")
    public List<Book> allPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return bookService.getAll(pageNumber, pageSize, "fio", Sort.Direction.ASC).getContent();
    }

    // поиск записей без постраничности (сразу весь список)
    @GetMapping(value = "/search")
    public List<Book> search(@RequestParam("fio") String fio) {
        return bookService.search(fio);
    }

    // поиск записей с постраничностью
    @GetMapping(value = "/searchPage")
    public List<Book> searchPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam("fio") String fio) {
        return bookService.search(pageNumber, pageSize, "fio", Sort.Direction.ASC, fio).getContent(); // т.к. возвращается объект Page, надо у него вызвать getContent, чтобы получить коллекцию
    }

    @GetMapping
    public Book get(@RequestParam("id") long id) {
        return bookService.get(id);
    }

    @DeleteMapping
    public boolean delete(@RequestParam("id") long id) {
        bookService.delete(bookService.get(id));// сначала получаем автора по id, потом его удаляем
        return true;
    }

    // добавить PDF к определенное книге
    @PostMapping(value = "/addContent")
    public boolean addContent(@RequestBody byte[] content, @RequestParam("bookId") long bookId) {
        Book book = bookService.get(bookId); // сначала получаем саму книгу
        book.setContent(content); // обновляем контент
        bookService.save(book); // сохраняем обратно в БД
        return true;
    }

    @PostMapping
    public boolean add(@RequestBody Book book) {
        bookService.save(book);
        return true;
    }

    // поиск списка книг по жанру
    @GetMapping(value = "/searchByGenre")
    public List<Book> getByGenre(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam("genreId") long genreId) {
        return bookService.findByGenre(pageNumber, pageSize, "name", Sort.Direction.ASC, genreId).getContent();
    }

    // получение PDF контента по id книги
    @GetMapping(value = "/getContent")
    public byte[] getContent(@RequestParam("bookId") long bookId) {
        return bookService.getContent(bookId);
    }
}
