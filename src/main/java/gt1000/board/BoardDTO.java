package gt1000.board;

import gt1000.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long id;

    private String title;

    private String content;

    private String memberId;
    private String name;
    private String email;

    private int replyCount;

    private LocalDateTime modifiedDate;

    private LocalDateTime createdDate;
}
