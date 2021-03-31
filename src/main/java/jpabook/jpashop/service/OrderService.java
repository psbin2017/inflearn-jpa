package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.delivery.Delivery;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송 정보를 생성한다.
        Delivery delivery = Delivery.createDelivery(member.getAddress());

        // 주문 상품을 생성한다.
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문을 생성한다.
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문을 저장한다.
        // CascadeType.ALL 이기 때문에 연관 관계를 가지는 엔티티는 자동으로 persist 된다.
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = orderRepository.findOne(orderId);

        // 주문을 취소한다.
        order.cancel();
    }

    public Order findOne(Long orderId) {
        return orderRepository.findOne(orderId);
    }

    public List<Order> findOrders(/* OrderSearch orderSearch */) {
        // return orderRepository.findAll(orderSearch);
        return null;
    }
}
