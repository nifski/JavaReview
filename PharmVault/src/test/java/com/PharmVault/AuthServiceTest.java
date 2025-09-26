package com.PharmVault;

import com.PharmVault.dto.RegisterRequest;
import com.PharmVault.entity.User;
import com.PharmVault.repo.UserRepository;
import com.PharmVault.service.AuthService;
import com.PharmVault.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    private RegisterRequest registerRequest;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest();
        registerRequest.setUsername("testuser");
        registerRequest.setEmail("test@example.com");
        registerRequest.setPassword("password123");
    }

    @Test
    void whenRegisterNewUser_thenSavesUserAndReturnsToken() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(jwtService.generateToken(any(User.class))).thenReturn("mockJwtToken");

        var response = authService.register(registerRequest);

        assertNotNull(response);
        assertEquals("mockJwtToken", response.getToken());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void whenRegisterWithExistingUsername_thenThrowsException() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(new User()));

        assertThrows(IllegalStateException.class, () -> {
            authService.register(registerRequest);
        });
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void whenRegisterWithExistingEmail_thenThrowsException() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(new User()));

        assertThrows(IllegalStateException.class, () -> {
            authService.register(registerRequest);
        });
        verify(userRepository, never()).save(any(User.class));
    }
}
