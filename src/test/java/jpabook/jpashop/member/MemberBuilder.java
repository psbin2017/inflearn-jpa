package jpabook.jpashop.member;

import jpabook.jpashop.domain.Member;

public class MemberBuilder {

    public static Member name(String name) {
        Member member = Member.builder()
                                .name(name)
                                .build();
        return member;
    }
}
