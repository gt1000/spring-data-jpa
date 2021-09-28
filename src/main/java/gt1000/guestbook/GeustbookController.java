package gt1000.guestbook;

import gt1000.common.dto.PageRequestDTO;
import gt1000.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/guestbooks")
@RestController
public class GeustbookController {

    private final GuestbookService guestbookService;

    @GetMapping
    public PageResultDTO<GuestbookDto, Guestbook> list() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        return guestbookService.getList(pageRequestDTO);
    }
}
