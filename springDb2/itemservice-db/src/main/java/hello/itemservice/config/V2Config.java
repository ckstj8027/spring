package hello.itemservice.config;

import hello.itemservice.jpa.JpaItemRepositoryV3;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.v2.ItemQueryRepositoryV2;
import hello.itemservice.repository.v2.ItemRepositoryV2;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import hello.itemservice.service.ItemServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Configuration
public class V2Config {


    private final EntityManager em;
    //Spring Data JPA는  JpaRepository<Item,Long> 를 상송받는인터페이스를 스캔하고, 런타임 시 ItemRepository의 구현체를 자동으로 생성
    //JpaRepository<Item,Long> 를 상속받는  인터페이스는 자동으로 빈으로 등록해준다 따라서 주입만 받으면됨
    private final ItemRepositoryV2 itemRepositoryV2;



    @Bean
    public ItemService itemService() {
        return new ItemServiceV2(itemRepositoryV2  ,itemQueryRepository() );
    }

    @Bean
    public ItemQueryRepositoryV2 itemQueryRepository() {
        return new ItemQueryRepositoryV2(em);

    }


    //  이거는 테스트 아이템 만들때 할때 쓰여서 남긴다 testInit 에 사용
    @Bean
    public  ItemRepository itemRepository(){
        return new JpaItemRepositoryV3(em);
    }



}
