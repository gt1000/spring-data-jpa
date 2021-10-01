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
@ToString(exclude = "member")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@SequenceGenerator(name = "movie_seq_generator", sequenceName = "movie_seq", allocationSize = 1)
@Table(name = "movie")
@Entity
public class Movie extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "movie_seq_generator")
    @Column(name = "movie_id", nullable = false)
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;
}
