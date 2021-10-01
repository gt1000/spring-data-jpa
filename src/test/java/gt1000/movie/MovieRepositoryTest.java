package gt1000.movie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieImageRepository movieImageRepository;

    @Commit
    @Transactional
    @Test
    @DisplayName("영화 등록")
    public void insert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder()
                    .title("영화" + i).build();
            System.out.println(" ---------------------------------- ");

            movieRepository.save(movie);
            int count = (int)(Math.random() * 5) + 1; // 1, 2, 3, 4

            for(int j=0; j<count; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .realFileName(UUID.randomUUID().toString())
                        .movie(movie)
                        .imageName("test" + j + ".jpg").build();

                movieImageRepository.save(movieImage);
            }

            System.out.println("========================================");
        });
    }

    @Test
    @DisplayName("목록")
    public void list() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));

        Page<Object[]> result = movieRepository.getListPage(pageRequest);
        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }
}