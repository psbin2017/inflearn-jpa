package jpabook.jpashop.web.dto;

import jpabook.jpashop.domain.item.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BookForm {

    private Long id;

    private String name;
    private long price;
    private int stockQuantity;
    private String author;
    private String isbn;

    public BookForm(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.price = book.getPrice();
        this.stockQuantity = book.getStockQuantity();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
    }
}
