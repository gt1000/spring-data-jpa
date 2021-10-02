package gt1000.member;

import gt1000.common.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PROTECTED;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@DynamicInsert
@DynamicUpdate
@Table(name = "club_member")
@Entity
public class ClubMember extends BaseTimeEntity implements Persistable<String> {

    @Id
    @Column(name = "club_member_id", nullable = false)
    private String clubMemberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(name = "from_social")
    private boolean fromSocial;

    @Override
    public String getId() {
        return clubMemberId;
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ClubMemberRole> roleSet = new HashSet<>();

    public void addMemberRole(ClubMemberRole clubMemberRole) {
        roleSet.add(clubMemberRole);
    }
}
