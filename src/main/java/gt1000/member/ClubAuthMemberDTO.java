package gt1000.member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ClubAuthMemberDTO extends User {
    private String clubMemberId;
    private String email;
    private String password;
    private String name;
    private boolean fromSocial;

    public ClubAuthMemberDTO(
            String username,
            String password,
            boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);
        this.clubMemberId = username;
        this.fromSocial = fromSocial;
    }
}
