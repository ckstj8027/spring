package hello.springtx.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void order(Order order) throws NotEnoughMoneyException {

        log.info("주문 프로세스 시작");
        orderRepository.save(order);

        log.info("결재 프로세스 진입 ");

        if(order.getUsername().equals("예외")){
            log.info("시스템 예외 발생 ");
            throw new RuntimeException("시스템 예외");  // 언체크 예외
        }else if(order.getUsername().equals("잔고부족")){
            log.info("잔고 부족 예외 발생 ");
            order.setPayStatus("대기");
            throw new NotEnoughMoneyException("잔고가 부족합니다");  // 체크 예외
        }
        else{
            log.info("정상 승인");
            order.setPayStatus("완료");
        }

        log.info("결제 프로세스 완료 ");

    }

}
