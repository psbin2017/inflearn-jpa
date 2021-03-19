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
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 주문의 주문 상품
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    // 주문의 배송 (FK)
    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // jdk 1.8 hidernate 가 자동 지원함
    private LocalDateTime orderDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus status;

}
