package gt1000.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class PasswordTest {

    @Test
    @DisplayName("Bcrypt 비밀번호")
    public void bcrypt() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String password = "1111";
        String encodePasword = bCryptPasswordEncoder.encode(password);
        log.info("password = 1111, encodePassword = {}", encodePasword);

        boolean matchResult = bCryptPasswordEncoder.matches(password, encodePasword);
        log.info("비교 결과 = {}", matchResult);
    }
}
