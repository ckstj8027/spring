package hello.itemservice.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.jpa.JpaItemRepositoryV2;
import hello.itemservice.jpa.JpaItemRepositoryV3;
import hello.itemservice.jpa.SpringDataJpaItemRepository;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Configuration
public class QuerydslConfig {


    private final EntityManager em;



    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }
    @Bean
    public ItemRepository itemRepository() {

        return new JpaItemRepositoryV3(em);
    }
}
