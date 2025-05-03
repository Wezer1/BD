package com.example.demo.service;

import com.example.demo.dto.AuthRequestDTO;
import com.example.demo.dto.AuthResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO request) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));//проводим аунтицикацию через email и пароль
            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));//если аунтификация успешна, с помощью email ищем пользователя
            String token = jwtTokenProvider.createToken(user);//если пользователь есть, то создаем токен
            AuthResponseDTO authResponseDTO = new AuthResponseDTO(user.getId(), user.getEmail(), user.getName(), user.getLastname(), user.getRole(),token);
            return ResponseEntity.ok(authResponseDTO);//передаем пользователя
    }
}
