package dailybook.hello.service;

import dailybook.hello.domain.User;
import dailybook.hello.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServerImpl implements UserDetailsService {

    private final UserRepo userRepo;

    public UserDetailServerImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with this username not exists" + username));

        return UserPrinciple.build(user);
    }

}
