package hello.itemservice.repository.mybatis;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    // 근데 이건 그냥 인터페이스고 구현체가 없는데 어캐MyBatisItemRepository 에서 ItemMapper 가  의존관계주입이 되었지 ?
    // 마이바티스 스프링 연동 모듈이 @Mapper 을 찾아서 프록시  객체로 구현체를  생성해준다
    // 이렇게 동적으로 만든 프록시 ItemMapper 객체를 스프링 빈으로 등록한다

    void save(Item item);


    void update(@Param("id") Long id, @Param("updateParam")ItemUpdateDto updateParam);


    Optional<Item> findById(Long id );

    List<Item> findAll(ItemSearchCond itemSearchCond);


}
