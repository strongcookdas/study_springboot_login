package com.login.study_springboot_login.login.domain.dto;

import com.login.study_springboot_login.login.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserJoinRequest {
    private String userName;
    private String password;
    private String emailAddress;

    public User toEntity(){
        return User.builder()
                .userName(this.userName)
                .password(this.password)
                .emailAddress(this.password)
                .emailAddress(this.emailAddress)
                .build();
    }
}
