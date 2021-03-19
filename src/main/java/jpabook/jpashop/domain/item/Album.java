package jpabook.jpashop.domain.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * `@DiscriminatorValue` 구분값
 */
@Entity
@DiscriminatorValue(value = "album")
@Getter
public class Album extends Item {

    private String artist;

    private String etc;

}
