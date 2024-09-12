package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Connection;
import java.sql.SQLException;

// 트랜잭션 템플릿
@Slf4j

public class MemberServiceV3_2 {
  //  private final DataSource dataSource;
  //  private final PlatformTransactionManager transactionManager;
    private final TransactionTemplate txTemplate;
    private final MemberRepositoryV3 memberRepository;

    public MemberServiceV3_2(PlatformTransactionManager transactionManager, MemberRepositoryV3 memberRepository) {
        this.txTemplate = new TransactionTemplate(transactionManager);
        this.memberRepository = memberRepository;
    }

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        //v_1  에서는
        //TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition()); 이거임

        txTemplate.executeWithoutResult((staus) -> {
            //비지니스로직
            try {
                bizLogic(fromId,toId,money);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }


        });
        //커밋: 비즈니스 로직이 예외 없이 성공적으로 실행되면, TransactionTemplate은 트랜잭션을 커밋합니다.
        //롤백: 비즈니스 로직 실행 중에 예외가 발생하면, 트랜잭션은 자동으로 롤백됩니다. 이는 데이터베이스의 상태를 이전으로 되돌리며, 일관성을 유지합니다.




    }

    private void bizLogic( String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById( fromId);
        Member toMember = memberRepository.findById( toId);

        memberRepository.update(fromId, fromMember.getMoney()- money);

        validation(toMember);

        memberRepository.update(toId, toMember.getMoney()+ money);
    }

    private static void release(Connection con) {
        if(con !=null){
            try {
                con.setAutoCommit(true);
                con.close();

            }
            catch (Exception e){
                log.info("error",e);

            }

        }
    }

    private static void validation(Member toMember) {
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체중 에외 발생");
        }
    }


}
