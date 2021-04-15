package com.luxoft.dbapp.servicesimpl;

import com.luxoft.dbapp.dao.security.UserDao;
import com.luxoft.dbapp.entities.security.Authority;
import com.luxoft.dbapp.entities.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(userName));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), convert(user.getAuthorities()));
    }

    private Set<? extends GrantedAuthority> convert(Set<Authority> inputSet) {
        if (inputSet != null && inputSet.size() > 0) {
            Set<SimpleGrantedAuthority> set = inputSet.stream()
                    .map(a -> a.getRole())
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
            return set;
        } else {
            return new HashSet<>();
        }
    }
}
