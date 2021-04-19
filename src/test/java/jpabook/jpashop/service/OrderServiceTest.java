package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderStatus;
import jpabook.jpashop.global.exception.NotEnoughStockException;
import jpabook.jpashop.item.ItemBuilder;
import jpabook.jpashop.member.MemberBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired private OrderService orderService;
    @Autowired private EntityManager em;

    @Test
    public void 상품주문() throws Exception {
        // given
        Member member = MemberBuilder.name("라이언");
        Item book = ItemBuilder.book();
        em.persist(member);
        em.persist(book);

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), 10);
        Order order = orderService.findOne(orderId);

        // then
        assertEquals( "주문의 최초 상태는 `ORDER`", OrderStatus.ORDER, order.getStatus() );
        assertEquals( "주문한 주문 상품 개수가 일치해야한다.", 1, order.getOrderItems().size() );
        assertEquals( "주문 가격은 상품 가격 * 수량 이다.", 300000, order.getTotalPrice() );
        assertEquals( "주문 후 상품 수량은 구매한 수량만큼 감소해야 한다.", 990, book.getStockQuantity() );
    }
    
    @Test
    public void 주문취소() throws Exception {
        // given
        Member member = MemberBuilder.name("제이지");
        Item album = ItemBuilder.album();
        em.persist(member);
        em.persist(album);
        
        // when
        Long orderId = orderService.order(member.getId(), album.getId(), 10 );
        orderService.cancelOrder(orderId);

        Order order = orderService.findOne(orderId);
        
        // then
        assertEquals( "주문의 취소 후 상태는 `CANCEL`", OrderStatus.CANCEL, order.getStatus() );
        assertEquals( "주문 취소 후 상품 수량은 구매한 수량만큼 다시 증가해야 한다.", 100000000, album.getStockQuantity() );
    }
    
    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        // given
        Member member = MemberBuilder.name("어피치");
        Item movie = ItemBuilder.movie();
        em.persist(member);
        em.persist(movie);

        // when
        // 기존 상품 최대 개수보다 하나 더 뺀다. (항상 음수)
        orderService.order(member.getId(), movie.getId(), movie.getStockQuantity() + 1 );
    }
}