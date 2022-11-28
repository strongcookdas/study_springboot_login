package com.login.study_springboot_login.login.service;

import com.login.study_springboot_login.login.domain.User;
import com.login.study_springboot_login.login.domain.dto.UserDto;
import com.login.study_springboot_login.login.domain.dto.UserJoinRequest;
import com.login.study_springboot_login.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public UserDto join(UserJoinRequest userJoinRequest){

        // 회원 중복 확인
        userRepository.findByUserName(userJoinRequest.getUserName())
                .ifPresent(user -> {
                    throw new RuntimeException("해당 UserName이 중복됩니다.");
                });

        // 회원 가입
        User savedUser = userRepository.save(userJoinRequest.toEntity());
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .password(savedUser.getPassword())
                .emailAddress(savedUser.getEmailAddress())
                .build();
    }
}
