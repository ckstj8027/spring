package hello.jdbc.service;


import hello.jdbc.domain.Member;
import hello.jdbc.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@SpringBootTest
class MemberServiceV4Test {

    public static final String MEMBER_A="memberA";
    public static final String MEMBER_B="memberB";
    public static final String MEMBER_EX="ex";


    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberServiceV4 memberService;

    @TestConfiguration
    static class TestConfig{

        @Autowired
        private  DataSource dataSource;
        
        @Bean
        MemberRepository memberRepository(){


          //  return new MemberRepositoryV4_1(dataSource);
           // return new MemberRepositoryV4_2(dataSource);
            //  SQLErrorCodeSQLExceptionTranslator 추가된거고 확인해볼려면
            //MemberRepositoryV4_2 들어가서 쿼리문 틀리게해서 배드그래머 클래스 예외 나오는거 확인해보면됨

            return new MemberRepositoryV5(dataSource);
        }
        @Bean
        MemberServiceV4 memberServiceV4(){return new MemberServiceV4(memberRepository());
        }

    }

    @Test
    void AopCheck() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberRepository class={}", memberRepository.getClass());
        Assertions.assertThat(AopUtils.isAopProxy(memberService)).isTrue();
        Assertions.assertThat(AopUtils.isAopProxy(memberRepository)).isFalse();
    }




    @AfterEach
    void after()  {
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }


    @Test
    @DisplayName("정상 이체")
    void accountTransfer()  {

        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_B, 10000);
        
        memberRepository.save(memberA);
        memberRepository.save(memberB);
        
        
        log.info("start tx");
        memberService.accountTransfer(memberA.getMemberId(),memberB.getMemberId(),2000);
        log.info("end tx");

        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberB = memberRepository.findById(memberB.getMemberId());

        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(8000);
        Assertions.assertThat(findMemberB.getMoney()).isEqualTo(12000);


    }

    @Test
    @DisplayName("이체중 예외발생")
    void accountTransferEx()  {

        Member memberA = new Member(MEMBER_A, 10000);
        Member memberEX = new Member(MEMBER_EX, 10000);

        memberRepository.save(memberA);
        memberRepository.save(memberEX);


        Assertions.assertThatThrownBy(()->memberService.accountTransfer(memberA.getMemberId(),memberEX.getMemberId(),2000))
                .isInstanceOf(IllegalStateException.class);



        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberEX = memberRepository.findById(memberEX.getMemberId());

        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(10000);
        Assertions.assertThat(findMemberEX.getMoney()).isEqualTo(10000);


    }












}



