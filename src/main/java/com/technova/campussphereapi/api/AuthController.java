package com.technova.campussphereapi.api;

import com.technova.campussphereapi.dto.AuthResponseDTO;
import com.technova.campussphereapi.dto.LoginDTO;
import com.technova.campussphereapi.dto.UserProfileDTO;
import com.technova.campussphereapi.dto.UserRegistrationDTO;
import com.technova.campussphereapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

    private final UserService userService;

    //Endpoint para registrar clientes
    @PostMapping("/register/student")
    public ResponseEntity<UserProfileDTO> registerStudent(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserProfileDTO userProfile = userService.registerStudent(userRegistrationDTO);
        return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO authResponse = userService.login(loginDTO);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
