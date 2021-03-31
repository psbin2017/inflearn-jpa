package jpabook.jpashop.member;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;

public class MemberBuilder {

    private static final String NAME = "라이언";
    private static final String CITY = "서울특별시";
    private static final String STREET = "종로구 서린동 청계천로";
    private static final String ZIPCODE = "1";

    public static Member name(String name) {
        return Member.createMember(name, address());
    }

    public static Member address(Address address) {
        return Member.createMember(NAME, address());
    }

    private static Address address() {
        return Address.builder()
                        .city(CITY)
                        .street(STREET)
                        .zipcode(ZIPCODE)
                        .build();
    }

}
