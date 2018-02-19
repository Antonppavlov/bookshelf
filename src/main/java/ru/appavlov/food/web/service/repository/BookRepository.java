package ru.appavlov.food.web.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.appavlov.food.web.service.model.Book;

import java.util.List;

// примечания:
// при получении списка книг - контент для каждой книги - пустой. Только когда пользователь нажимает на чтение книги - делаем запрос в БД на получение контента (по требованию)
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // поиск книг по названию или автору
    // если имя метода получается слишком длинным - можно использовать @Query с HQL
    List<Book> findByNameContainingIgnoreCaseOrAuthorFioContainingIgnoreCaseOrderByName(String name, String fio);


}
