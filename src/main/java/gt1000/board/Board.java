package gt1000.board;

import gt1000.common.entity.BaseTimeEntity;
import gt1000.member.Member;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

@Setter
@Getter
@Builder
@ToString(exclude = "member")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@SequenceGenerator(name = "board_seq_generator", sequenceName = "board_seq", allocationSize = 1)
@Table(name = "board")
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "board_seq_generator")
    @Column(name = "board_id", nullable = false)
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void changeTile(String tile) {
        this.title = tile;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
