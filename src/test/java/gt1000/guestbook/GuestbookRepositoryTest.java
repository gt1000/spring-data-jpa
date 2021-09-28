package gt1000.guestbook;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    @DisplayName("querydsl 테스트")
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
}