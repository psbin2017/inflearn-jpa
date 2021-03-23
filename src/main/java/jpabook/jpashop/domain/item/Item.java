package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.global.exception.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
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

    public Item(String name, long price, int stockQuantity, List<Category> categories) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categories = categories;
    }

    //== 비즈니스 로직 ==//
    // 엔티티에 관한 비즈니스 로직을 엔티티가 직접 가지고 있음

    /**
     * 수량 증가
     * @param quantity 증가될 수량
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * 수량 감소
     * @param quantity 감소될 수량
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if ( restStock < 0 ) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
