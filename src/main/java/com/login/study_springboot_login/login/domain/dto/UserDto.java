package com.login.study_springboot_login.login.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private String emailAddress;

    public UserJoinResponse toResponse(){
        return UserJoinResponse.builder()
                .userName(this.userName)
                .emailAddress(this.emailAddress)
                .build();
    }
}
