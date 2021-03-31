package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.order.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 하나의 주문 상품은 하나의 상품을 가진다.
 */
@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    /*
     * 단방향 매핑
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // 주문 상품의 주문 (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private long orderPrice;

    private int count;

    // === 연관관계 편의 메소드 === //

    public void setOrder(Order order) {
        this.order = order;
    }

    // === 생성 메소드 === //

    public static OrderItem createOrderItem(Item item, long orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        // 기존 재고에서 차감한다
        item.removeStock(count);
        return orderItem;
    }

    // === 비즈니스 메소드 === //

    public void cancel() {
        getItem().addStock(count);
    }

    // === 조회 메소드 === //

    /**
     * 주문 상품 전체 가격을 반환한다.
     */
    public long getTotalPrice() {
        return orderPrice * count;
    }
}
