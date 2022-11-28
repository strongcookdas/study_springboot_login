package com.login.study_springboot_login.login.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.study_springboot_login.login.domain.dto.UserDto;
import com.login.study_springboot_login.login.domain.dto.UserJoinRequest;
import com.login.study_springboot_login.login.exception.ErrorCode;
import com.login.study_springboot_login.login.exception.HospitalReviewAppException;
import com.login.study_springboot_login.login.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    void join_success() throws Exception {
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("id")
                .password("password")
                .emailAddress("email@gmail.com")
                .build();

        when(userService.join(any())).thenReturn(mock(UserDto.class));

        mockMvc.perform(post("/api/v1/user/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 실패")
    void join_fail() throws Exception {

        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("name")
                .password("password")
                .emailAddress("email@gmail.com")
                .build();

        when(userService.join(any())).thenThrow(new
                HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, ""));
        mockMvc.perform(post("/api/v1/user/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}