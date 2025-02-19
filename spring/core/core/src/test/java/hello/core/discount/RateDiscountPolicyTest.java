package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip는 10퍼 할인이 적용되어야 한다 ")
    void vip_o(){
        //giver
        Member member = new Member(1L,"memberVIP", Grade.VIP);
        //when
        int discount =discountPolicy.discount(member,10000);

        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }


    @Test
    @DisplayName("vip가아니면 10퍼 할인이 적용되지않음 ")
    void vip_x(){
        //giver
        Member member = new Member(1L,"memberBASIC", Grade.BASIC);
        //when
        int discount =discountPolicy.discount(member,10000);

        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }

}