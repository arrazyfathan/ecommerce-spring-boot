package com.arrazyfathan.ecommerce.service;


import com.arrazyfathan.ecommerce.component.JwtUtils;
import com.arrazyfathan.ecommerce.data.entity.UserEntity;
import com.arrazyfathan.ecommerce.data.model.LoginRequest;
import com.arrazyfathan.ecommerce.data.model.RegisterRequest;
import com.arrazyfathan.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public Boolean register(RegisterRequest registerRequest) {
        Optional<UserEntity> user = userRepository.findByPhoneNumber(registerRequest.getPhoneNumber());
        if (user.isPresent()) {
            return false;
        } else {
            try {
                UserEntity userEntity = new UserEntity();
                userEntity.setPhoneNumber(registerRequest.getPhoneNumber());
                userEntity.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
                userEntity.setName(registerRequest.getName());

                userRepository.save(userEntity);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public String login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getPhoneNumber(),
                loginRequest.getPassword()
        );

        authenticationManager.authenticate(authenticationToken);
        UserEntity userEntity = userRepository.findByPhoneNumber(loginRequest.getPhoneNumber()).orElseThrow();

        return jwtUtils.generateToken(userEntity);
    }

}
