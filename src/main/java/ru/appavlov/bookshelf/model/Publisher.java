package ru.appavlov.bookshelf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(catalog = "library")
@EqualsAndHashCode(of = "id")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@Getter
@Setter
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    @Override
    public String toString() {
        return name;
    }
}
