package gt1000.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("게시물 저장")
    public void save() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("jpa study")
                .content("jpa 왜 이리 어렵노?")
                .memberId("test")
                .name("허준")
                .email("test@test.com")
                .build();
        boardService.save(boardDTO);
    }
}