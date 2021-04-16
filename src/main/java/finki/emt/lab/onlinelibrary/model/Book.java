package finki.emt.lab.onlinelibrary.model;

import finki.emt.lab.onlinelibrary.model.enums.CategoryEnum;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @OneToOne
    private Author author;

    private Integer availableCopies;

    public Book(String name, CategoryEnum category, Author author) {
        this.name = name;
        this.category = category;
        this.author = author;
    }
}
