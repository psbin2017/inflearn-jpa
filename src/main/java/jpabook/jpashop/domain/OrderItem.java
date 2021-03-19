package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.order.Order;
import lombok.Getter;

import javax.persistence.*;

/**
 * 하나의 주문 상품은 하나의 상품을 가진다.
 */
@Entity
@Getter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    /*
     * 단방향 매핑
     */
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    // 주문 상품의 주문 (FK)
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private long orderPrice;

    private int count;

}
