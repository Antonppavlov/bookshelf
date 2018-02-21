package ru.appavlov.bookshelf.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.appavlov.bookshelf.model.Book;

import java.util.List;

// примечания:
// при получении списка книг - контент для каждой книги - пустой. Только когда пользователь нажимает на чтение книги - делаем запрос в БД на получение контента (по требованию)
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // поиск книг по названию или автору
    // если имя метода получается слишком длинным - можно использовать @Query с HQL
    List<Book> findByNameContainingIgnoreCaseOrAuthorFioContainingIgnoreCaseOrderByName(String name, String fio);

    Page<Book> findByNameContainingIgnoreCaseOrAuthorFioContainingIgnoreCaseOrderByName(String name, Pageable pageable);

    @Query("select new ru.appavlov.bookshelf.model.Book(b.id, b.name, b.pageCount, b.isbn, b.genre, b.author, b.publisher, b.publishYear, b.image, b.descr, b.viewCount, b.totalRating, b.totalVoteCount, b.avgRating) from Book b")
    Page<Book> findAllWithoutContent(Pageable pageable); // возвращает список книг (с постраничностью)

    @Query("select new ru.appavlov.bookshelf.model.Book(b.id, b.name, b.pageCount, b.isbn, b.genre, b.author, b.publisher, b.publishYear, b.descr, b.viewCount, b.totalRating, b.totalVoteCount, b.avgRating) from Book b")
    Page<Book> findAllWithoutContentAndImage(Pageable pageable); // возвращает список книг (с постраничностью)

    // Для топовых книг показываем только изображение (поэтому остальные поля не выбираем)
    // В классе Book должен быть соответствующий конструктор
    @Query("select new ru.appavlov.bookshelf.model.Book(b.id, b.image) from Book b")
    List<Book> findTopBooks(Pageable pageable);

    // Получение название и конент книги по id
    // В классе Book должен быть соответствующий конструктор
    @Query("select  b.content from Book b where b.id =:id")
    byte[] findContentBook(@Param("id") long id);

    //Получение списка книг по жанру (с объектом Page)
    //по id genre
    @Query("select new ru.appavlov.bookshelf.model.Book(b.id, b.name, b.pageCount, b.isbn, b.genre, b.author, b.publisher, b.publishYear, b.descr, b.viewCount, b.totalRating, b.totalVoteCount, b.avgRating) from Book b  where b.genre.id =:genreId")
    Page<Book> findBooksWhereGenre(@Param("genreId") long genreId, Pageable pageable); // возвращает список книг (с постраничностью)

    // если запрос изменяет данные - нужен @Modifying
    @Modifying(clearAutomatically = true)
    @Query("update Book b set b.viewCount=:viewCount where b.id =:id")
    void updateViewCount(long viewCount, long id);

    // если запрос изменяет данные - нужен @Modifying
    @Modifying(clearAutomatically = true)
    @Query("update Book b set b.totalRating=:totalRating, b.totalVoteCount=:totalVoteCount,b.avgRating=:avgRating where b.id =:id")
    void updateRating(long totalRating, long totalVoteCount, int avgRating, long id);

    // если запрос изменяет данные - нужен @Modifying
    @Modifying(clearAutomatically = true)
    @Query("update Book b set b.content=:content where b.id =:id")
    void updateContent(@Param("content") byte[] content, @Param("id") long id);
}
