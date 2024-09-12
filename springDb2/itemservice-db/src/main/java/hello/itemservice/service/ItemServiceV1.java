package hello.itemservice.service;

import hello.itemservice.domain.Item;
import hello.itemservice.jpa.JpaItemRepositoryV2;
import hello.itemservice.jpa.JpaItemRepositoryV3;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
@RequiredArgsConstructor
public class ItemServiceV1 implements ItemService {

    private final ItemRepository itemRepository;

   //  private final JpaItemRepositoryV2 itemRepositoryV2;   이런식으로 왜 직접 안부르냐 ? 효율성이냐 아니면 구조적 ocp di 잘 지킨 설계냐 ? 선택의 문제

    @Override
    public Item save(Item item) {

        return itemRepository.save(item);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        itemRepository.update(itemId, updateParam);


    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> findItems(ItemSearchCond cond) {

        return itemRepository.findAll(cond);
    }
}
