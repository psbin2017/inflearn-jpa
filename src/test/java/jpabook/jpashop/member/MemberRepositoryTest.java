package jpabook.jpashop.member;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link MemberRepository} 에서 CTRL + SHIFT + T
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * @Transactional
     * 1. 테스트 코드에 있으면 실행된 작업을 롤백함
     *      ... 롤백을 하지않고 싶다면 @Rollback(false)
     *
     * 2. 리포지토리와 메소드에 해당 어노테이션이 없으면 엔티티 매니저가 트랜잭션 처리를 할 수 없어 `InvalidDataAccessApiUsageException` 가 발생함.
     *      ... No EntityManager with actual transaction available for current thread
     *
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception {
        // given
        Member member = MemberBuilder.name("홍길동");

        // when
        Long saveId = memberRepository.save(member);
        // select query 실행 안함 (영속성 컨텍스트 1차 캐시에 이미 가지고 있음)
        Member findMember = memberRepository.find(saveId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

}