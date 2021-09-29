package gt1000.board;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long save(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        boardRepository.save(board);

        return board.getId();
    }
}
