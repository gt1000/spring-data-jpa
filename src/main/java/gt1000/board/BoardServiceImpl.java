package gt1000.board;

import gt1000.common.dto.PageRequestDTO;
import gt1000.common.dto.PageResultDTO;
import gt1000.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardReplyRepository boardReplyRepository;
    private final ModelMapper modelMapper;

    @Override
    public BoardDTO get(Long id) {
        Object result = boardRepository.getBoardById(id);
        Object[] arr = (Object[])result;

        Board board = (Board)arr[0];
        Member member = (Member)arr[1];
        Long replyCount = (Long)arr[2];
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        boardDTO.setMemberId(member.getMemberId());
        boardDTO.setEmail(member.getEmail());
        boardDTO.setName(member.getName());
        return boardDTO;
    }

    @Override
    public Long save(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        boardRepository.save(board);

        return board.getId();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info("--- pageRequestDTO = {}", pageRequestDTO);

        Function<Object[], BoardDTO> function = (en -> {
            Board board = (Board)en[0];
            Member member = (Member)en[1];
            Long replyCount = (Long)en[2];
            BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
            boardDTO.setMemberId(member.getMemberId());
            boardDTO.setEmail(member.getEmail());
            boardDTO.setName(member.getName());
            return boardDTO;
        });

//        Page<Object[]> result = boardRepository.getBoardWithReplyCount(
//                pageRequestDTO.getPageable(Sort.by("id").descending()));

        Page<Object[]> result = boardRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("id").descending())
        );

        return new PageResultDTO<>(result, function);
    }

    public void update(BoardDTO boardDTO) {
        Board board = boardRepository.getById(boardDTO.getId());
        board.changeTile(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());
        boardRepository.save(board);
    }

    @Transactional
    public void deleteWithReply(Long id) {
        boardReplyRepository.deleteByBoardId(id);
        boardRepository.deleteById(id);
    }
}
