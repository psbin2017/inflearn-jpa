package jpabook.jpashop.member;

public class MemberBuilder {

    public static Member name(String username) {
        Member member = Member.builder()
                                .username(username)
                                .build();
        return member;
    }
}
