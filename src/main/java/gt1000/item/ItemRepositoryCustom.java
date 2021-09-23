package gt1000.item;

import java.util.List;

public interface ItemRepositoryCustom {
    List<ItemDto> search(ItemSearchCondition condition);
}
