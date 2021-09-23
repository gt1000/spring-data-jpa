package gt1000.item;

import java.util.List;

public interface ItemService {
    List<Item> findByItemName(String itemName);
}
