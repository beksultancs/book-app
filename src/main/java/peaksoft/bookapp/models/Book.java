package peaksoft.bookapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Getter @Setter
public class Book {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_id_generator"
    )
    @SequenceGenerator(
            name = "book_id_generator",
            sequenceName = "book_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String name;

    private String author;

    private int price;

    public Book() {
    }

    public Book(String name, String author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }
}
