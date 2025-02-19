package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepository;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

// 예외누수 문제 해결
// throws SQLException 제거하고
// MemberRepository 인터페이스에 의존하게 됨
@Slf4j

public class MemberServiceV4 {
  //  private final DataSource dataSource;
  //  private final PlatformTransactionManager transactionManager;

    private final MemberRepository memberRepository;

    public MemberServiceV4(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Transactional
    public void accountTransfer(String fromId, String toId, int money)  {
        //v_1  에서는
        //TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition()); 이거임


            //비지니스로직

            bizLogic(fromId,toId,money);



    }

    private void bizLogic( String fromId, String toId, int money)  {
        Member fromMember = memberRepository.findById( fromId);
        Member toMember = memberRepository.findById( toId);

        memberRepository.update(fromId, fromMember.getMoney()- money);

        validation(toMember);

        memberRepository.update(toId, toMember.getMoney()+ money);
    }



    private static void validation(Member toMember) {
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체중 에외 발생");
        }
    }


}
