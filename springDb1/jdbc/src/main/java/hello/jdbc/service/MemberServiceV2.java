package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {
    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;


    public void accountTransfer(String fromId,String toId,int money) throws SQLException {

        Connection con = dataSource.getConnection();
        try {
            con.setAutoCommit(false);  // 트렌잭션 시작
            // 비지니스 로직 시작
            bizLogic(con, fromId, toId, money);

            con.commit();  // 정상 로직 수행되면 커밋
        }catch (Exception e){
                    // 오류생기면 롤백
            con.rollback();
            throw new IllegalStateException(e);
        }
        finally {
            release(con);

        }





    }

    private void bizLogic(Connection con, String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);

        memberRepository.update(con,fromId, fromMember.getMoney()- money);

        validation(toMember);

        memberRepository.update(con,toId, toMember.getMoney()+ money);
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
