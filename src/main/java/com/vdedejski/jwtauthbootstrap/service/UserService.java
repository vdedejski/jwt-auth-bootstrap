package com.vdedejski.jwtauthbootstrap.service;

import com.vdedejski.jwtauthbootstrap.model.User;
import com.vdedejski.jwtauthbootstrap.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(UserDto userDto);

    Long getWorkersCount();
}
