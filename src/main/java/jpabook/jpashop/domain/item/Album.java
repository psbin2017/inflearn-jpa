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
@DiscriminatorValue(value = "album")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Album extends Item {

    private String artist;

    private String etc;

    @Builder
    public Album(String name, long price, int stockQuantity, List<Category> categories, String artist, String etc) {
        super(name, price, stockQuantity, categories);
        this.artist = artist;
        this.etc = etc;
    }
}
