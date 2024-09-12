package hello.itemservice;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;

    /**
     * 확인용 초기 데이터 추가
     *
     * @PostConstruct 를 사용해도 되지만  @Transactional 과 함께
     * 사용 할경우 aop 가 다 처리 안된 상태에서 호출될수 있기 때문에
     * @EventListener(ApplicationReadyEvent.class) 를 사용하면
     * aop 를 포함한 스프링 컨테이너가 완전히 초기화 된 이후에 호출되기 때문에
     * 이런 문제가 발생하지 않는다
     *
     *
     */

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("test data init");
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
