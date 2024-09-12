package hello.itemservice.jpa;

import hello.itemservice.domain.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface   SpringDataJpaItemRepository extends JpaRepository<Item,Long> {

    List<Item> findByItemNameLike(String itemName);

    List<Item> findByPriceLessThanEqual(Integer price);

    // 밑에 직접 쿼리문 작성한것과 동일 차라리 복잡한경우는 직접 쿼리문 작성 하는게 유리 그리고 이경우 @Param 으로 넘겨줘야함
    List<Item> findByItemNameLikeAndPriceLessThanEqual(String itemName,Integer price);


    @Query("select i from Item i where i.itemName like :itemName and i.price <= :price")
    List<Item> findItems(@Param("itemName") String itemName , @Param("price") Integer price);

    // 그러면 findAll 은 어딨지 ? 인터페이스가 자동 제공 이미 만들어 놓음



}
