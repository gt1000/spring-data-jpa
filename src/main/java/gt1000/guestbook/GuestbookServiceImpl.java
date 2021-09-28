package gt1000.guestbook;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import gt1000.common.dto.PageRequestDTO;
import gt1000.common.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository guestbookRepository;
    private final ModelMapper modelMapper;

    @Override
    public GuestbookDto get(Long id) {
        Guestbook guestbook = guestbookRepository.getById(id);
        return modelMapper.map(guestbook, GuestbookDto.class);
    }

    @Override
    public PageResultDTO<GuestbookDto, Guestbook> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());

        BooleanBuilder builder = getSearchBuilder(pageRequestDTO);
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        Function<Guestbook, GuestbookDto> function = (entity -> modelMapper.map(entity, GuestbookDto.class));

        return new PageResultDTO<>(result, function);
    }

    @Override
    public Long save(GuestbookDto guestbookDto) {
        Guestbook guestbook = modelMapper.map(guestbookDto, Guestbook.class);
        guestbookRepository.save(guestbook);

        return guestbook.getId();
    }

    @Override
    public void update(GuestbookDto guestbookDto) {
        Optional<Guestbook> result = guestbookRepository.findById(guestbookDto.getId());
        if(result.isPresent()) {
            Guestbook guestbook = result.get();
            guestbook.changeTile(guestbookDto.getTitle());
            guestbook.changeContent(guestbookDto.getContent());

            guestbookRepository.save(guestbook);
        }
    }

    @Override
    public void delete(Long id) {
        guestbookRepository.deleteById(id);
    }

    private BooleanBuilder getSearchBuilder(PageRequestDTO pageRequestDTO) {
        String type = pageRequestDTO.getType();
        BooleanBuilder builder = new BooleanBuilder();

        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = pageRequestDTO.getKeyword();

        BooleanExpression expression = qGuestbook.id.gt(0L);

        builder.and(expression);
        if(type == null || type.trim().length() == 0) {
            return builder;
        }

        BooleanBuilder condition = new BooleanBuilder();
        if(type.contains("t")) {
            condition.or(qGuestbook.title.contains(keyword));
        } else if(type.contains("c")) {
            condition.or(qGuestbook.content.contains(keyword));
        } else if(type.contains("w")) {
            condition.or(qGuestbook.writer.contains(keyword));
        }

        builder.and(condition);
        return builder;
    }
}
