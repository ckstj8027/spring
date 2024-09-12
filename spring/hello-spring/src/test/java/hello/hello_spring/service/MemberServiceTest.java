package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemoryMemberRepository memberRepository;
    MemberService memberService;
    @BeforeEach
    public void beforeEach(){
        memberRepository=new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);

    }



    @AfterEach
    public void afferEach(){
        memberRepository.clearStore();
    }






    @Test
    void 회원가입() {
        //given
        Member member=new Member();
        member.setName("hello");
        //when
        Long saveId=memberService.join(member);

        //then
        //Assertions.assertThat(saveId).isEqualTo(member.getId());

        Member findMember = memberService.findOne(saveId).get();

        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
    }

    @Test
    void 중복회원저장() {
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");
        //when
        Long saveId1=memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage().equals("이미 존재하는 회원입니다"));

//        try {
//            Long saveId2=memberService.join(member2);
//            fail();
//
//        }catch ( IllegalStateException e){
//            Assertions.assertThat(e.getMessage().equals("이미 존재하는 회원입니다"));
//
//        }


        //then
        //Assertions.assertThat(saveId).isEqualTo(member.getId());

        Member findMember = memberService.findOne(saveId1).get();


        Assertions.assertThat(member1.getId()).isEqualTo(findMember.getId());
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}