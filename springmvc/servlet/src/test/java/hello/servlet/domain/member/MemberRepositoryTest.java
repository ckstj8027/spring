package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository=MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        Member member=new Member("kim",20);
        Member savedMember=memberRepository.save(member);
        Member findMember=memberRepository.findById(savedMember.getId());

        Assertions.assertThat(savedMember).isEqualTo(findMember);



    }

    @Test
    void findAll(){
        Member member1=new Member("kim",20);
        Member member2=new Member("Lee",30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> members = memberRepository.findAll();

        for (Member member : members) {
            System.out.println(member.getId());
            System.out.println(member.getAge());
            System.out.println(member.getUsername());
            System.out.println("-------------");
        }

        Assertions.assertThat(members.size()).isEqualTo(2);
        Assertions.assertThat(members).contains(member1,member2);

    }

}