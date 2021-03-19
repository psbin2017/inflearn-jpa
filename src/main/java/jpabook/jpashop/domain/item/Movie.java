package jpabook.jpashop.domain.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * `@DiscriminatorValue` 구분값
 */
@Entity
@DiscriminatorValue(value = "movie")
@Getter
public class Movie extends Item {

    private String director;

    private String actor;

}

