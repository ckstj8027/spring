package hello.jdbc.service;


import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
@SpringBootTest
class MemberServiceV3_4Test {

    public static final String MEMBER_A="memberA";
    public static final String MEMBER_B="memberB";
    public static final String MEMBER_EX="ex";


    @Autowired
    private MemberRepositoryV3 memberRepository;
    @Autowired
    private MemberServiceV3_3 memberService;

    @TestConfiguration
    static class TestConfig{

        @Autowired
        private  DataSource dataSource;
        
        @Bean
        MemberRepositoryV3 memberRepositoryV3(){
            return new MemberRepositoryV3(dataSource);
        }
        @Bean
        MemberServiceV3_3 memberServiceV3_3(){
            return new MemberServiceV3_3(memberRepositoryV3());
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
    void after() throws SQLException {
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }


    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws SQLException {

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
    void accountTransferEx() throws SQLException {

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



