package hello.itemservice.jpa;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.Item;
import hello.itemservice.domain.QItem;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static hello.itemservice.domain.QItem.item;

@Slf4j
@Repository
@Transactional
public class JpaItemRepositoryV3 implements ItemRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaItemRepositoryV3(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Item save(Item item) {
          em.persist(item);
          return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = em.find(Item.class, itemId);

        // 이렇게 하면 jpa가알아서 업데이트 쿼리 만들어 나감  그럼 언제 ? -> @Transactional 커밋되는 시점에 밑의 업데이트된 정보 나감
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());

    }

    @Override
    public Optional<Item> findById(Long id) {
        Item findItem = em.find(Item.class, id);
        return Optional.ofNullable(findItem);
    }


    public List<Item> findAllOld(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        BooleanBuilder builder=new BooleanBuilder();
        if(StringUtils.hasText(itemName)){
            builder.and(item.itemName.like("%"+itemName+"%"));
        }
        if(maxPrice!=null){
            builder.and(item.price.loe(maxPrice));
        }

       // QItem item=new QItem("i");
        List<Item> result = query.select(item)
                .from(item)
                .where(builder)
                .fetch();

        return result;


    }


    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

//        BooleanBuilder builder=new BooleanBuilder();
//        if(StringUtils.hasText(itemName)){
//            builder.and(item.itemName.like("%"+itemName+"%"));
//        }
//        if(maxPrice!=null){
//            builder.and(item.price.loe(maxPrice));
//        }

        // QItem item=new QItem("i");
        List<Item> result = query.select(item)
                .from(item)
                .where(likeItemName(itemName),maxPrice(maxPrice))
                .fetch();

        return result;


    }

    private Predicate maxPrice(Integer maxPrice) {
        if(maxPrice!=null){
            return item.price.loe(maxPrice);
        }
        return null;
    }

    private BooleanExpression likeItemName(String itemName){
        if(StringUtils.hasText(itemName)){
            return item.itemName.like("%"+itemName+"%");
        }
        return null;
        
        
    }
}

