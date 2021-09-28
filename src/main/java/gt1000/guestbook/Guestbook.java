package gt1000.guestbook;

import gt1000.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@SequenceGenerator(name = "guestbook_seq_generator", sequenceName = "guestbook_seq", allocationSize = 1)
@Table(name = "guestbook")
@Entity
public class Guestbook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "guestbook_seq_generator")
    @Column(name = "guestbook_id", nullable = false)
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    public void changeTile(String tile) {
        this.title = tile;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
