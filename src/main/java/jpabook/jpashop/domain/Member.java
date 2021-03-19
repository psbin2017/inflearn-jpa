package jpabook.jpashop.domain;

import jpabook.jpashop.domain.order.Order;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 하나의 회원은 여러 개의 주문을 가진다.
 */
@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    /*
     * 양방향 매핑
     * mappedBy: 반대쪽 매핑의 필드 명을 값으로 준다.
     */
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String name, Address address, List<Order> orders) {
        this.name = name;
        this.address = address;
        this.orders = orders;
    }

}
