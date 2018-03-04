package ru.appavlov.bookshelf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(catalog = "library")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Author {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String fio;

    private Date birthday;

    @JsonIgnore
    @Basic(fetch = FetchType.LAZY, optional = false)
    @OneToMany(mappedBy = "author")
    private List<Book> books;

    @Override
    public String toString() {
        return fio;
    }
}
