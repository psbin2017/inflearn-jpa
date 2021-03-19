package jpabook.jpashop.domain.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * `@DiscriminatorValue` 구분값
 */
@Entity
@DiscriminatorValue(value = "book")
@Getter
public class Book extends Item {

    private String author;

    private String isbn;

}
