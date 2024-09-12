package hello.itemservice.config;

import hello.itemservice.jpa.JpaItemRepositoryV2;
import hello.itemservice.jpa.SpringDataJpaItemRepository;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class SpringDataJpaConfig {


    private final SpringDataJpaItemRepository springDataJpaItemRepository;


    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }
    @Bean
    public ItemRepository itemRepository() {

        return new JpaItemRepositoryV2(springDataJpaItemRepository);
    }
}
