package gt1000.guestbook;

import gt1000.common.dto.PageRequestDTO;
import gt1000.common.dto.PageResultDTO;

public interface GuestbookService {
    PageResultDTO<GuestbookDto, Guestbook> getList(PageRequestDTO pageRequestDTO);

    GuestbookDto get(Long id);

    Long save(GuestbookDto guestbookDto);

    void update(GuestbookDto guestbookDto);

    void delete(Long id);
}
