package jpabook.jpashop.domain;

import jpabook.jpashop.domain.order.Order;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 하나의 회원은 여러 개의 주문을 가진다.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
     *
     * `= new ArrayList<>();`
     * 하이버네이트가 엔티티 내부 컬렉션을 내장 컬렉션으로 변경하는데 잘못된 생성시 문제가 발생한다.
     * 그래서 필드 레벨로 생성하는 것이 가장 안전하고, 코드도 간결하다.
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
