package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * `@NoArgsConstructor(access = AccessLevel.PROTECTED)`
 * - JPA 엔티티/임베디드 타입은 스펙상(리플렉션과 프록시와 연관된 기능 등) 기본 생성자를 선언해야 한다.
 *   `public` 또는 `protected` 로 선언 해야하는데 `protected` 로 제한한다.
 *
 * `@Embeddable`
 * - 값 타입 객체
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class Address {

    private String city;

    private String street;

    private String zipcode;

    @Builder
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
