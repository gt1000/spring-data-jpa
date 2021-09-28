package gt1000.guestbook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/guestbooks")
@RestController
public class GeustbookController {

    @GetMapping
    public ResponseEntity<GestbookDto> list() {
        log.info("---- 목록 ---");
        return null;
    }
}
