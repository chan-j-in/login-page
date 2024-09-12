package com.study.loginPage.service;

import com.study.loginPage.dto.UserInfoDto;
import com.study.loginPage.entity.UserInfo;
import com.study.loginPage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public Long save(UserInfoDto infoDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        UserInfo userInfo = UserInfo.builder()
                .email(infoDto.getEmail())
                .password(infoDto.getPassword())
                .auth(infoDto.getAuth()).build();
        userRepository.save(userInfo);
        return userInfo.getCode();
    }
}
