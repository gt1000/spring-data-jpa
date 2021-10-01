package gt1000.movie;

import gt1000.common.entity.BaseTimeEntity;
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
@SequenceGenerator(name = "movie_image_seq_generator", sequenceName = "movie_image_seq", allocationSize = 1)
@Table(name = "movie_image")
@Entity
public class MovieImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "movie_image_seq_generator")
    @Column(name = "movie_image_id", nullable = false)
    private Long id;

    @Column(name = "real_file_name", nullable = false)
    private String realFileName;

    @Column(name = "image_name")
    private String imageName;

    private String path;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
