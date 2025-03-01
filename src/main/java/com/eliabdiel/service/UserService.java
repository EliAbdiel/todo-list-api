package com.eliabdiel.service;

import com.eliabdiel.infra.exception.ApiException;
import com.eliabdiel.infra.security.JwtTokenProvider;
import com.eliabdiel.infra.util.UserRole;
import com.eliabdiel.model.dto.JwtAuthResponse;
import com.eliabdiel.model.dto.LoginDto;
import com.eliabdiel.model.dto.RegisterDto;
import com.eliabdiel.model.role.Role;
import com.eliabdiel.model.user.UserEntity;
import com.eliabdiel.repository.user_role.RoleRepository;
import com.eliabdiel.repository.user_role.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<?> register(RegisterDto registerDto) {
        try {
            if (userRepository.existsByUsername(registerDto.username())) {
                throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Username already exists");
            }
            if (userRepository.existsByEmail(registerDto.email())) {
                throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Email already exists");
            }

            var user = new UserEntity(registerDto);

            Set<Role> roles = new HashSet<>();

            var userRole = roleRepository.findByName(UserRole.ROLE_INDEX + UserRole.USER)
                    .orElseThrow(() -> new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Role USER not found"));

            roles.add(userRole);
            user.setRoles(roles);

            userRepository.save(user);

            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }

    public ResponseEntity<?> login(LoginDto loginDto) {
        try {
            var authentication = this.authentication(loginDto);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);

            JwtAuthResponse authResponse = new JwtAuthResponse();
            authResponse.setAccessToken(token);

            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }

    private Authentication authentication(LoginDto loginDto) {

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.usernameOrEmail(),
                        loginDto.password()
                )
        );
    }
}
