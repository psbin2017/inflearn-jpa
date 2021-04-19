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
@DiscriminatorValue(value = "movie")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Movie extends Item {

    private String director;

    private String actor;

    @Builder
    public Movie(Long id, String name, long price, int stockQuantity, List<Category> categories, String director, String actor) {
        super(id, name, price, stockQuantity, categories);
        this.director = director;
        this.actor = actor;
    }
}

