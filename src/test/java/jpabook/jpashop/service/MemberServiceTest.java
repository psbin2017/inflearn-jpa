package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.member.MemberBuilder;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired private MemberService memberService;
    @Autowired private MemberRepository memberRepository;

    /**
     * `@Rollback(value = false)` 미 선언시 스프링이 관리하는 트랜잭션이 영속성 컨텍스트 플러시를 진행하지 않기 때문에 insert 쿼리가 로그로 나오지 않음
     * ... o.s.t.c.transaction.TransactionContext   : Rolled back transaction for test
     *
     * 보고 싶다면 엔티티 매니저를 직접 플러시 하거나 `@Rollback(value = false)` 를 선언하여 롤백을 취소한다.
     */
    @Test
    @Rollback(value = false)
    public void join_success() throws Exception {
        // given
        Member member = MemberBuilder.name("홍길동");

        // when
        Long saveId = memberService.join(member);

        // then
        Assert.assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void join_fail_validateDuplicateMember() throws Exception {
        // given
        Member member1 = MemberBuilder.name("홍길동");
        Member member2 = MemberBuilder.name("홍길동");

        // when
        memberService.join(member1);
        memberService.join(member2);
    }

    @Test
    public void findMembers() throws Exception {
        // given

        // when

        // then
    }

    @Test
    public void findOne() throws Exception {
        // given

        // when

        // then
    }
}