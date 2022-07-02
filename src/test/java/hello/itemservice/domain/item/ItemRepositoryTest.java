package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA",1000,12);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        Assertions.assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findByI() {
    }

    @Test
    void findAll() {
        //given
        Item item = new Item("itemA",1000,12);
        Item item2 = new Item("itemB",1000,12);

        itemRepository.save(item);
        itemRepository.save(item2);
        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item, item2);

    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("item1",10000, 10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item itemParams = new Item("item2", 1000, 200);
        itemRepository.update(itemId,itemParams);

        Item findItem = itemRepository.findById(item.getId());


        //then
        assertThat(findItem.getItemName()).isEqualTo(itemParams.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(itemParams.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(itemParams.getQuantity());
    }
}