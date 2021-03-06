package com.vdedejski.jwtauthbootstrap.model.dto;

import com.vdedejski.jwtauthbootstrap.model.enumerations.Role;
import lombok.Data;

@Data
public class UserDto {
    private String username;

    private String password;

    private String repeatPassword;

    private Role role;

    public UserDto(String username, String password, String repeatPassword, Role role) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.role = role;
    }
}
