package hello.jdbc.repository;


import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 *
 *jdbcTemplate 사용
 *
 *
 **/
@Slf4j
public class MemberRepositoryV5 implements MemberRepository {

    private final JdbcTemplate template;


    @Autowired
    public MemberRepositoryV5(DataSource dataSource) {
        this.template=new JdbcTemplate(dataSource);

    }



    @Override
    public Member save(Member member) {

        String sql = "insert into member(member_id, money) values (?, ?)";
        template.update(sql,member.getMemberId(),member.getMoney());
        return member;

        // 커넥션 받아오고 pstmt 받아와서 실행하고 예외반환까지 다 해줌
    }

    @Override
    public Member findById(String memberId) {
        String sql="select * from member where member_id=? ";
        Member member = template.queryForObject(sql, memberRowMapper(), memberId);
        return member;
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs,rowNum)->{
            Member member=new Member();
            member.setMemberId(rs.getString("member_id"));
            member.setMoney(rs.getInt("money"));
            return member;
        };
    }






    @Override
    public void update(String memberId,int money)  {
        String sql="update member set money=? where member_id=?";
        template.update(sql,money,memberId);

    }







    @Override
    public void delete(String memberId)  {
        String sql="delete from member where member_id=?";
        template.update(sql,memberId);

    }



// 커넥션 동기화와 닫는것도 다해줌









}
