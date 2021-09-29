package gt1000.board;

import gt1000.common.entity.BaseTimeEntity;
import gt1000.member.Member;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

@Setter
@Getter
@Builder
@ToString(exclude = {"board", "member"})
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@SequenceGenerator(name = "board_reply_seq_generator", sequenceName = "board_reply_seq", allocationSize = 1)
@Table(name = "board_reply")
@Entity
public class BoardReply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "board_reply_seq_generator")
    @Column(name = "board_reply_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void changeContent(String content) {
        this.content = content;
    }
}
