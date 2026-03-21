package com.jorgedev.inventoriCoreApiRestFull.auth;

import com.jorgedev.inventoriCoreApiRestFull.security.JwtService;
import com.jorgedev.inventoriCoreApiRestFull.security.Role;
import com.jorgedev.inventoriCoreApiRestFull.security.User;
import com.jorgedev.inventoriCoreApiRestFull.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        // 1. Creamos al usuario con los datos que llegaron de Bruno
        User user = User.builder()
                .username(request.getUsername())
                // Encriptamos la contraseña antes de guardarla
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER) // Le asignamos el rol por defecto
                .build();

        // 2. Lo guardamos en la base de datos MySQL
        userRepository.save(user);

        // 3. Le fabricamos su Token VIP
        String jwtToken = jwtService.getToken(user);

        // 4. Se lo devolvemos en el sobre de respuesta
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        // 1. Spring Security revisa si el usuario y la contraseña coinciden
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // 2. Si todo está bien, buscamos al usuario en la base de datos
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        // 3. Le fabricamos un nuevo Token
        String jwtToken = jwtService.getToken(user);

        // 4. Se lo devolvemos
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}