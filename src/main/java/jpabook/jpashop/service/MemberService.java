package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    /**
     * 회원 중복 확인: 이름이 중복되는 경우 중복으로 간주한다.
     * <p>
     * @apiNote 인프라가 멀티 인스턴스 구조인 경우, 동시에 요청시 해당 코드만으로는 중복 문제가 발생할 수 있다.
     *          따라서 회원 테이블에 이름 컬럼에 유니크를 주어 데이터베이스 수준으로 방지해야 한다.
     */
    private void validateDuplicateMember(Member member) {
        if ( ! memberRepository.findByName(member.getName()).isEmpty() ) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
