package jpabook.jpashop.domain.order;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.delivery.Delivery;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 하나의 주문은 여러 개의 주문 상품을 가진다.
 * 하나의 주문은 하나의 배송을 가진다.
 */
@Entity
@Table(name = "orders")
@Getter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    // 주문한 회원 (FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 주문의 주문 상품
    /*
     * `CascadeType.ALL`
     * 전파 조건, 주문에 대한 `em.persist()` 시 같이 주문 상품도 반영된다.
     *
     * ex)
     * em.persist(orderItem1); em.persist(orderItem2); em.persist(orderItem3); em.persist(order1);
     * 인 코드를 `CascadeType.ALL` 설정시
     * em.persist(order1);
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 주문의 배송 (FK)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // jdk 1.8 hidernate 가 자동 지원함
    private LocalDateTime orderDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

    // === 연관관계 편의 메소드 === //

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        if ( ! this.orderItems.contains(orderItem) ) {
            this.orderItems.add(orderItem);
        }
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
