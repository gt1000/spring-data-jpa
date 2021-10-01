package gt1000.movie;

import gt1000.common.entity.BaseTimeEntity;
import gt1000.member.Member;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

@Setter
@Getter
@Builder
@ToString(exclude = {"movie", "member"})
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@SequenceGenerator(name = "review_seq_generator", sequenceName = "review_seq", allocationSize = 1)
@Table(name = "review")
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "review_seq_generator")
    @Column(name = "review_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private int grade;
    private String title;
}
