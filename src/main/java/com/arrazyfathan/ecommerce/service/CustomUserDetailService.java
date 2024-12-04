package com.arrazyfathan.ecommerce.service;

import com.arrazyfathan.ecommerce.data.entity.UserEntity;
import com.arrazyfathan.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(username)
                .map(this::mapFromEntity)
                .orElseThrow();
    }

    private UserDetails mapFromEntity(UserEntity userEntity) {
        return User.builder()
                .username(userEntity.phoneNumber)
                .password(userEntity.password)
                .build();
    }
}
