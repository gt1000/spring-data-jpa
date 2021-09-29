package gt1000.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("사용자 등록")
    public void insert() {

        Member member = Member.builder()
            .memberId("test")
            .name("홍길동")
            .password("test")
            .email("test@test.com")
            .build();

        memberRepository.save(member);
    }
}