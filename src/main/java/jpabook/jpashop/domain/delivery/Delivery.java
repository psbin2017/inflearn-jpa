package jpabook.jpashop.domain.delivery;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.order.Order;
import lombok.Getter;

import javax.persistence.*;

/**
 * 하나의 배송은 하나의 주문을 가진다.
 */
@Entity
@Getter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    /*
     * 양방향 매핑
     * mappedBy: 반대쪽 매핑의 필드 명을 값으로 준다.
     */
    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "delivery_status")
    private DeliveryStatus status;

}
