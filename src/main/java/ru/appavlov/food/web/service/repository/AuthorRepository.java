package ru.appavlov.food.web.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.appavlov.food.web.service.model.Author;

import java.util.List;

@Repository // специальный Spring bean, который помечает интерфейс как Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {// JpaRepository - содержит CRUD функционал + постраничность

    // на основании имени метода будет построен Hibernate запрос
    List<Author> findByFioContainingIgnoreCaseOrderByFio(String fio);

}
