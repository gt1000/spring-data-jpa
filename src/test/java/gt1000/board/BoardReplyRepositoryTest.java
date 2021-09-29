package gt1000.board;

import gt1000.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BoardReplyRepositoryTest {

    @Autowired
    private BoardReplyRepository boardReplyRepository;

    // TODO join 이 recursive 하게 되네.... 내 생각과 다른데...
    @Test
    @DisplayName("한건 조회")
    public void read() {
        Optional<BoardReply> result = boardReplyRepository.findById(5L);
        if(result.isPresent()) {
            BoardReply boardReply = result.get();
            log.info("-------------- reply = {}", boardReply);
        }
    }

    @Test
    @DisplayName("게시물 답글")
    public void insert() {
        Member member = Member.builder()
                .memberId("test")
                .name("홍길동")
                .build();

        IntStream.rangeClosed(1, 300).forEach(i -> {

            long boardId = (long)(Math.random() * 100) + 1;

            Board board = Board.builder()
                    .id(boardId)
                    .build();

            BoardReply boardReply = BoardReply.builder()
                    .content("reply" + i)
                    .board(board)
                    .member(member)
                    .build();

            boardReplyRepository.save(boardReply);
        });
    }
}