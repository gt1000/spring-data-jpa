package gt1000.board;

import gt1000.common.dto.PageRequestDTO;
import gt1000.common.dto.PageResultDTO;

public interface BoardService {
    BoardDTO get(Long id);

    Long save(BoardDTO boardDTO);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    void update(BoardDTO boardDTO);

    void deleteWithReply(Long id);
}
