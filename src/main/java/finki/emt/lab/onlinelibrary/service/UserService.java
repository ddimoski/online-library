package finki.emt.lab.onlinelibrary.service;

import finki.emt.lab.onlinelibrary.model.User;
import finki.emt.lab.onlinelibrary.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.management.relation.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(String username, String password, String role) {
        String encryptedPassword = this.passwordEncoder.encode(password);
        if (username.isEmpty() || password.isEmpty() || role == null)
            return null;
        else {
            User user = new User(username, encryptedPassword, role);
            return this.userRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElseThrow();
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
            user.getUsername(), user.getPassword(), grantedAuthorities
        );

        return userDetails;
    }
}
