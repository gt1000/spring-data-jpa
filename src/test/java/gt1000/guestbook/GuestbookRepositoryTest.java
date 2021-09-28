package gt1000.guestbook;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.InputStream;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    @DisplayName("더미 데이터 입력")
    public void insertDummy() {
        IntStream.range(1, 300).forEach( i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("테스트" + i)
                    .content("내용" + i)
                    .writer("작성자" + i)
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    @Test
    @DisplayName("수정 테스트")
    public void updateGuestbook() {
        Optional<Guestbook> result = guestbookRepository.findById(1507L);
        if(result.isPresent()) {
            Guestbook guestbook = result.get();
            guestbook.changeTile("수정 제목100");
            guestbook.changeContent("내용을 수정 했습니다. 1");

            guestbookRepository.save(guestbook);
        }
    }

    @Test
    @DisplayName("querydsl 제목 검색")
    public void searchTitle() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qGuestbook.title.contains(keyword);
        builder.and(expression);

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test
    @DisplayName("다중 항목 검색")
    public void searchTileOrContent() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expressionTile = qGuestbook.title.contains(keyword);
        BooleanExpression expressionContent = qGuestbook.content.contains(keyword);
        BooleanExpression expressionAll = expressionTile.and(expressionContent);
        builder.and(expressionAll);
        builder.and(qGuestbook.id.gt(500L));

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }
}