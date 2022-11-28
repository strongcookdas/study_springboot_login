package com.login.study_springboot_login.login.controller;

import com.login.study_springboot_login.login.domain.Response;
import com.login.study_springboot_login.login.domain.dto.UserDto;
import com.login.study_springboot_login.login.domain.dto.UserJoinRequest;
import com.login.study_springboot_login.login.domain.dto.UserJoinResponse;
import com.login.study_springboot_login.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserRestController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest){
        UserDto userDto = userService.join(userJoinRequest);
        return Response.success(userDto.toResponse());
    }
}
