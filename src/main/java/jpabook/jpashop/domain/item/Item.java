package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * abstract class 임을 주목
 *
 * `@Inheritance`: 상속 관계 매핑은 상속 관계 전략을 지정해야 한다.
 * `@DiscriminatorColumn` 구분자 컬럼 명
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private long price;

    private int stockQuantity;

    /*
     * 다대다 연관관계: 중간 테이블로 연결
     */
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}
