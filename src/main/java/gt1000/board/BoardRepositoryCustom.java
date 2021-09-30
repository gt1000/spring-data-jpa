package gt1000.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Board search();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
