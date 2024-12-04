package com.arrazyfathan.ecommerce.service;


import com.arrazyfathan.ecommerce.data.entity.UserEntity;
import com.arrazyfathan.ecommerce.data.model.RegisterRequest;
import com.arrazyfathan.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public Boolean register(RegisterRequest registerRequest) {
        UserEntity user = userRepository.findByPhoneNumber(registerRequest.getPhoneNumber());
        if (user != null) {
            return false;
        } else {
            try {
                UserEntity userEntity = new UserEntity();
                userEntity.setPhoneNumber(registerRequest.getPhoneNumber());
                userEntity.setPassword(registerRequest.getPassword());
                userEntity.setName(registerRequest.getName());

                userRepository.save(userEntity);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

}
