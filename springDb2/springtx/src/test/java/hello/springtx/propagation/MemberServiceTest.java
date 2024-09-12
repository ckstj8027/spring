package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;


@Slf4j
@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LogRepository logRepository;



//
//     memberService  @Transactional:off
//
//     memberRepository  @Transactional:on
//
//     logRepository   @Transactional:on
    @Test
    void outerTxOff_success(){
        String username="outerTxOff_success";

        memberService.joinV1(username);

        Assertions.assertTrue(memberRepository.find(username).isPresent());
        Assertions.assertTrue(logRepository.find(username).isPresent());

    }




    //
//     memberService  @Transactional:off
//
//     memberRepository  @Transactional:on
//
//     logRepository   @Transactional:on exception
    // 와부에 없고 각각 있는경우 독립적으로 작용
    @Test
    void outerTxOff_fail(){
        String username=" 로그예외_outerTxOff_fail ";



        org.assertj.core.api.Assertions.assertThatThrownBy(()-> memberService.joinV1(username) )
                        .isInstanceOf(RuntimeException.class);

        Assertions.assertTrue(memberRepository.find(username).isPresent());
        Assertions.assertTrue(logRepository.find(username).isEmpty());

    }


    //
//     memberService  @Transactional:on
//
//     memberRepository  @Transactional:off
//
//     logRepository   @Transactional:off
    @Test
    void singleTx(){
        String username="outerTxOff_success";

        memberService.joinV1(username);

        Assertions.assertTrue(memberRepository.find(username).isPresent());
        Assertions.assertTrue(logRepository.find(username).isPresent());

    }


    //
//     memberService  @Transactional:on
//
//     memberRepository  @Transactional:on
//
//     logRepository   @Transactional:on
    @Test
    void outerTxOn_success(){
        String username="outerTxOn_success";

        memberService.joinV1(username);

        Assertions.assertTrue(memberRepository.find(username).isPresent());
        Assertions.assertTrue(logRepository.find(username).isPresent());

    }




    //
//     memberService  @Transactional:on
//
//     memberRepository  @Transactional:on
//
//     logRepository   @Transactional:on exception
    @Test
    void outerTxOn_fail(){
        String username=" 로그예외_outerTxOn_fail ";



        org.assertj.core.api.Assertions.assertThatThrownBy(()-> memberService.joinV1(username) )
                .isInstanceOf(RuntimeException.class);

        Assertions.assertTrue(memberRepository.find(username).isEmpty());
        Assertions.assertTrue(logRepository.find(username).isEmpty());

    }


    //
//     memberService  @Transactional:on
//
//     memberRepository  @Transactional:on
//
//     logRepository   @Transactional:on exception
    @Test
    void recoverException_fail(){
        String username=" 로그예외_recoverException_fail ";



        org.assertj.core.api.Assertions.assertThatThrownBy(()-> memberService.joinV2(username) )
                .isInstanceOf(UnexpectedRollbackException.class);

        Assertions.assertTrue(memberRepository.find(username).isEmpty());
        Assertions.assertTrue(logRepository.find(username).isEmpty());

// service 에서 오류를 잡아도 어처피 logRepository 에서 rollback-only 를 마크해서 물리 트랜잭션에서 커밋하기전에 확인해봤는데 rollback-only 가 있어서 롤백
    }


    //
//     memberService  @Transactional:on
//
//     memberRepository  @Transactional:on
//
//     logRepository   @Transactional:on(Required_new) exception
    @Test
    void recoverException_success(){
        String username=" 로그예외_recoverException_fail ";



        memberService.joinV2(username);


        Assertions.assertTrue(memberRepository.find(username).isPresent());
        Assertions.assertTrue(logRepository.find(username).isEmpty());

    }


}