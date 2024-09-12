package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //private final MemberRepository memberRepository=new MemoryMemberRepository();

    private final MemberRepository memberRepository;
    @Autowired//ac.getBean(MemberRepository.class)
    //이는 내부적으로 스프링 컨테이너가 다음과 같은 작업을 수행하는 것과 같습니다
    //this.memberRepository = applicationContext.getBean(MemberRepository.class);
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }



    //테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
