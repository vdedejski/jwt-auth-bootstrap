package com.vdedejski.jwtauthbootstrap.service.impl;

import com.vdedejski.jwtauthbootstrap.model.User;
import com.vdedejski.jwtauthbootstrap.model.dto.UserDto;
import com.vdedejski.jwtauthbootstrap.model.enumerations.Role;
import com.vdedejski.jwtauthbootstrap.model.exceptions.InvalidUsernameOrPasswordException;
import com.vdedejski.jwtauthbootstrap.model.exceptions.PasswordsDoNotMatchException;
import com.vdedejski.jwtauthbootstrap.model.exceptions.UsernameExistsException;
import com.vdedejski.jwtauthbootstrap.repository.UserRepository;
import com.vdedejski.jwtauthbootstrap.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(UserDto userDto) {
        if (userDto.getUsername() == null || userDto.getUsername().isEmpty() || userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }

        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            throw new PasswordsDoNotMatchException();
        }

        if (this.userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new UsernameExistsException(userDto.getUsername());
        }

        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getRole());
        return this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }

    @Override
    public Long getWorkersCount() {
        return this.userRepository.countAllByRole(Role.ROLE_WORKER);
    }
}
