package gt1000.board;

import gt1000.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("검색")
    public void search() {
        boardRepository.search();
    }

    @Test
    @DisplayName("검색 페이지")
    public void searchPage() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        Page<Object[]> result = boardRepository.searchPage("t", "1", pageable);
    }

    @Test
    @DisplayName("조회")
    public void read() {
        Optional<Board> result = boardRepository.findById(50L);
        if(result.isPresent()) {
            Board board = result.get();
            log.info("board = {}", board);
            log.info("----------------------------------");
            log.info("board.member_id = {}", board.getMember().getMemberId());
        }
    }

    @Test
    @DisplayName("댓글 개수")
    public void replyCount() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
        result.get().forEach(row -> {
            Object[] arr = (Object[])row;
            System.out.println("--- = " + Arrays.toString(arr));
        });
    }

    @Test
    @DisplayName("게시물 등록")
    public void insert() {
        Member member = Member.builder()
                .memberId("test")
                .build();

        // TODO 람다 scope 가 맞나? 기억이 가물 가물, 귀찮아서 사용했는데.... final 취급을 해서 인가?
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("테스트" + i)
                    .content("테스트 내용 입니다." + i)
                    .member(member)
                    .build();
            boardRepository.save(board);
        });
    }
}