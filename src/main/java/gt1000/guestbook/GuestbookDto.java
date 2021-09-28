package gt1000.guestbook;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestbookDto {
    private Long id;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime modifiedDate;

    private LocalDateTime createdDate;
}
