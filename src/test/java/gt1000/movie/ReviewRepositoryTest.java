package gt1000.movie;

import gt1000.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @DisplayName("리뷰 등록 테스트")
    public void insertMovieReview() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Long movieId = (long)(Math.random() * 100) + 1;

            //Long reviewId = ((long)(Math.random()*100) + 1);
            Member member = Member.builder()
                    .memberId("test")
                    .build();

            Review review = Review.builder()
                    .member(member)
                    .movie(Movie.builder().id(movieId).build())
                    .grade((int)(Math.random()* 5) + 1)
                    .title("영화 감상 리뷰" + i)
                    .build();

            reviewRepository.save(review);
        });
    }
}