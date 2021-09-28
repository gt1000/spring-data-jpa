package gt1000.guestbook;

import gt1000.common.dto.PageRequestDTO;
import gt1000.common.dto.PageResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class GuestbookServiceTest {

    @Autowired
    private GuestbookService guestbookService;

    @Test
    @DisplayName("방명록 등록")
    public void save() {
        GuestbookDto guestbookDto = GuestbookDto.builder()
                .title("model mapper 테스트")
                .content("예제 내용")
                .writer("test0")
                .build();

        guestbookService.save(guestbookDto);
    }

    @Test
    @DisplayName("방명록 목록")
    public void list() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<GuestbookDto, Guestbook> resultDTO = guestbookService.getList(pageRequestDTO);

        System.out.println("prev : " + resultDTO.isPrev());
        System.out.println("next : " + resultDTO.isNext());
        System.out.println("total : " + resultDTO.getTotalPage());

        for (GuestbookDto guestbookDto : resultDTO.getList()) {
            System.out.println(guestbookDto);
        }

        System.out.println("------------------");
        resultDTO.getPageList().stream().forEach(i -> System.out.println(i));
    }

    @Test
    @DisplayName("검색")
    public void search() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("t")
                .keyword("테스트").build();

        PageResultDTO<GuestbookDto, Guestbook> resultDTO = guestbookService.getList(pageRequestDTO);

        for(GuestbookDto guestbookDto : resultDTO.getList()) {
            log.info(" guestbookDto = {}", guestbookDto);
        }
    }
}