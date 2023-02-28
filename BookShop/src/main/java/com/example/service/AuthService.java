package com.example.service;

import com.example.config.JwtService;
import com.example.dto.auth.AuthenticationRequestDTO;
import com.example.dto.auth.AuthenticationResponseDTO;
import com.example.dto.auth.RegisterRequestDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    public AuthenticationResponseDTO register(RegisterRequestDTO request) {
        var profile = ProfileEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ProfileRole.USER)
                .build();
        profileRepository.save(profile);

        var jwtToken = jwtService.generateToken(profile);
        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );

        var profile = profileRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(profile);
        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }
}
