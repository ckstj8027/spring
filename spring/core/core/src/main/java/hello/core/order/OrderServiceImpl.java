package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

   // private final MemberRepository  memberRepository=new MemoryMemberRepository();

    //private final DiscountPolicy discountPolicy=new FixDiscountPolicy();

    //private final DiscountPolicy discountPolicy=new RateDiscountPolicy();

    // 위에 2개는 dip 와 ocp 다 위배  하지만 이렇게 하는경우 구현체가없는데 어캐 실행하냐 ?
    //-> 따라서 AppConfig 만들어서 생성자 주입을 사용 이렇게 하면 여기 코드 자체적으로는 추상화에만 의존 구현체에는 의존 안해도됨 dip 지킴
    // 그리고 위에처럼 갈아끼우면서 수정 할 필요도없음 -> ocp 도 지킴



    private final DiscountPolicy discountPolicy;



    private final MemberRepository  memberRepository;

    @Autowired
    private  DiscountPolicy rateDiscountPolicy;

    @Autowired
   public OrderServiceImpl(@MainDiscountPolicy DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
       this.memberRepository = memberRepository;
  }




    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member=memberRepository.findById(memberId);
        int discountPrice =discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
