package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

// 트랜잭션 매니저
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_1 {
  //  private final DataSource dataSource;
    private final PlatformTransactionManager transactionManager;
    private final MemberRepositoryV3 memberRepository;


    public void accountTransfer(String fromId,String toId,int money) throws SQLException {
        // 트랜잭션 시작
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());


        try {

            // 비지니스 로직 시작
            bizLogic( fromId, toId, money);

            transactionManager.commit(status);  // 정상 로직 수행되면 커밋
        }catch (Exception e){
                    // 오류생기면 롤백
            transactionManager.rollback(status);
            throw new IllegalStateException(e);
        }



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
