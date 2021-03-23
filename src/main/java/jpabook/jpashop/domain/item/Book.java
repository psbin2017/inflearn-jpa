package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

/**
 * `@DiscriminatorValue` 구분값
 */
@Entity
@DiscriminatorValue(value = "book")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Book extends Item {

    private String author;

    private String isbn;

    @Builder
    public Book(String name, long price, int stockQuantity, List<Category> categories, String author, String isbn) {
        super(name, price, stockQuantity, categories);
        this.author = author;
        this.isbn = isbn;
    }

}
