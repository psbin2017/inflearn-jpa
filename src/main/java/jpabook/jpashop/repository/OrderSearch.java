package jpabook.jpashop.repository;

import jpabook.jpashop.domain.order.OrderStatus;
import lombok.Getter;

@Getter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
