package hello.itemservice.jpa;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository  // 참고로 예외가 터지면 SpringDataJpa가 만들어주는 추상화에서 이미 예외처리 변환을 해준다 뭐 중복되도 상관없음
@Transactional
public class JpaItemRepositoryV2 implements ItemRepository {

    @Autowired
    private final  SpringDataJpaItemRepository repository;

    // 이렇게 따로 JpaItemRepositoryV2 를 만들어서   SpringDataJpaItemRepository 쓰는 이유가 머냐면 서비스에서 SpringDataJpaItemRepository 직접사용하게되면
    //이거는 JpaRepository<Item,Long>를 상속 받기때문에 기존에는 ItemRepository 인터페이스 타입을 사용 따라서 ItemRepository를 상속받는 클래스를 따로만든뒤에
    // 이 클래스에서  SpringDataJpaItemRepository 를 주입 받아 쓰면 좋은 방법이됨
    //JpaItemRepository 에서도 em 을 주입 받아서 쓰자나  service 에서 em 불러다 쓰지않고 그런 맥략이라 이해
    //즉 adaptor 역활을 해준다고 보면됨

    public JpaItemRepositoryV2(SpringDataJpaItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = repository.findById(itemId).orElseThrow();



        // 이렇게 하면 jpa가알아서 업데이트 쿼리 만들어 나감  그럼 언제 ? -> @Transactional 커밋되는 시점에 밑의 업데이트된 정보 나감
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());

    }

    @Override
    public Optional<Item> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName=cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();


        if(StringUtils.hasText(itemName) && maxPrice !=null){
            return repository.findItems("%"+ itemName+"%",maxPrice);
        }
        else if(StringUtils.hasText(itemName)){
            return repository.findByItemNameLike("%"+ itemName+"%");
        }
        else if (maxPrice !=null){
            return repository.findByPriceLessThanEqual(maxPrice);

        }
        else {
            return repository.findAll();
        }
    }
}

