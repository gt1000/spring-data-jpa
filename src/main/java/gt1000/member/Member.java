package gt1000.member;

import gt1000.common.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@DynamicInsert
@DynamicUpdate
@Table(name = "member")
@Entity
public class Member extends BaseTimeEntity implements Persistable<String> {

    @Id
    @Column(name = "member_id", nullable = false)
    private String memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Override
    public String getId() {
        return memberId;
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
