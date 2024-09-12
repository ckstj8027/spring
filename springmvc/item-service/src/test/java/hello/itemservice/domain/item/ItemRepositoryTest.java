package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

     ItemRepository itemRepository= new ItemRepository();

     @AfterEach
    void afterEach(){
         itemRepository.clearStore();
     }

     @Test
             void save(){
         Item item=new Item("Item",10000,10);

         Item saveItem=itemRepository.save(item);

         Item findItem = itemRepository.findById(item.getId());

         Assertions.assertThat(findItem).isEqualTo(saveItem);
     }

     @Test
    void findAll(){

         Item itemA=new Item("ItemA",10000,10);

         Item itemB=new Item("ItemB",20000,20);


         itemRepository.save(itemA);

         itemRepository.save(itemB);


         List<Item> all = itemRepository.findAll();


         Assertions.assertThat(all).contains(itemA,itemB);



     }


    @Test
    void updateItem() {

        Item itemA = new Item("ItemA", 10000, 10);

        Item itemB = new Item("ItemB", 20000, 20);


        itemRepository.save(itemA);

        itemRepository.save(itemB);


        Item itemNew = new Item("ItemNew", 100000, 120);


        itemRepository.update(itemA.getId(),itemNew);

        Assertions.assertThat(itemA.getItemName()).isEqualTo("ItemNew");





    }





    }