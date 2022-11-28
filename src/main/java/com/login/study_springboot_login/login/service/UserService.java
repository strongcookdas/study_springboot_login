package com.login.study_springboot_login.login.service;

import com.login.study_springboot_login.login.domain.dto.UserDto;
import com.login.study_springboot_login.login.domain.dto.UserJoinRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserDto join(UserJoinRequest userJoinRequest){
        return new UserDto();
    }
}
