package ru.appavlov.bookshelf.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.appavlov.bookshelf.model.Vote;

import java.util.List;

@Repository // специальный Spring bean, который помечает интерфейс как Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {// JpaRepository - содержит CRUD функционал + постраничность

    // на основании имени метода будет построен Hibernate запрос
    List<Vote> findByUsernameContainingIgnoreCaseOrderByUsername(String username);

    // Page cодержит результаты выполнения запроса и служебные данные для постраничности
    Page<Vote> findByUsernameContainingIgnoreCaseOrderByUsername(String username, Pageable pageable);// Pageable - параметры для постраничности
}
