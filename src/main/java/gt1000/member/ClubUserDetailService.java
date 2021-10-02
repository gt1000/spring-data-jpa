package gt1000.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class ClubUserDetailService implements UserDetailsService {

    private final ClubMemberRepository clubMemberRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("---------------- ClubUserDetailService.loadUserByUsername");

        Optional<ClubMember> result = clubMemberRepository.findByEmail(username, false);
        // 여기 이상한데...? 맞나?
        if(result.isEmpty()) {
            throw new UsernameNotFoundException("Check Email or Social");
        }

        ClubMember clubMember = result.get();
        ClubAuthMemberDTO clubAuthMemberDTO = modelMapper.map(clubMember, ClubAuthMemberDTO.class);
        clubMember.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_") + role.name()).collect(Collectors.toSet());

        return clubAuthMemberDTO;
    }
}
