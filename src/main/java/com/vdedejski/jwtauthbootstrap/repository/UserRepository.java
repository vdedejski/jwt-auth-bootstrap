package com.vdedejski.jwtauthbootstrap.repository;

import com.vdedejski.jwtauthbootstrap.model.User;
import com.vdedejski.jwtauthbootstrap.model.enumerations.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Long countAllByRole(Role role);
}
