package gt1000.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    private void createItemList() {
        for(int i=1; i<11; i++) {
            Item item = new Item();
            item.setItemName("테스트 상품" + i);
            item.setPrice(1000 + i);
            item.setSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100 + i);
            item.setItemDetail("상품 테스트 상세 설명" + i);

            Item saveItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        Item item = new Item();
        item.setItemName("테스트 상품");
        item.setPrice(1000);
        item.setSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setItemDetail("상품 테스트 상세 설명");

        Item saveItem = itemRepository.save(item);
        System.out.println(saveItem);

    }

    @Test
    @DisplayName("상품명 검색 테스트")
    public void findByItemNameTest() {
        this.createItemList();

        List<Item> items = itemRepository.findByItemName("테스트 상품1");
        items.forEach( t -> {
            System.out.println(t);
        });
    }

    @Test
    @DisplayName("상품명 또는 상세 설명 검색 테스트")
    public void findByItemNameOrItemDetailTest() {
        List<Item> items = itemRepository.findByItemNameOrItemDetail("테스트 상품1", "테스트 상품 상세 설명1");
        for(Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("@Query 를 이용한 상품 상세 정보 검색")
    public void findByItemDetailTest() {
        List<Item> items = itemRepository.findByItemDetail("테스트 상품 상세 설명");
        for (Item item : items) {
            System.out.println(item);
        }
    }
}