package hello.itemservice.config;

import hello.itemservice.TestDataInit;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.memory.MemoryItemRepository;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MemoryConfig {

//    @Bean
//    @Profile("local")
//    public TestDataInit testDataInit(ItemRepository itemRepository) {
//
//        return new TestDataInit(itemRepository);
//    }



    // 서비스와 리포지토리의 구현체를 편리하게 바꿔기고 그러기 위해서 수동 빈으로 하는 형태로 프젝 실시

    @Bean
    public ItemService itemService() {

        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {

        return new MemoryItemRepository();
    }

}
