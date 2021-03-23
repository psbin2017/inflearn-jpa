package jpabook.jpashop.item;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Movie;

public class ItemBuilder {

    public static Book book() {
        return Book.builder()
                        .name("자바 표준 ORM 프로그래밍")
                        .price(30000)
                        .stockQuantity(1000)
                        .author("김영한")
                        .isbn("978-1-78280-808-4")
                        .build();
    }

    public static Movie movie() {
        return Movie.builder()
                        .name("아무도 없는 곳")
                        .price(10000)
                        .stockQuantity(100000000)
                        .actor("연우진, 김상호, 이지은, 이주영, 윤혜리")
                        .director("김종관")
                        .build();
    }

    public static Album album() {
        return Album.builder()
                        .name("LILAC")
                        .price(20000)
                        .stockQuantity(100000000)
                        .artist("아이유")
                        .etc("2021년 3월 25일")
                        .build();
    }

}
