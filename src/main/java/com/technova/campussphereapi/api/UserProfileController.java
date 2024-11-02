package com.technova.campussphereapi.api;

import com.technova.campussphereapi.dto.UserProfileDTO;
import com.technova.campussphereapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/profile")
@RequiredArgsConstructor
@PreAuthorize("hasRole('STUDENT')") // Permitir solo a Student
public class UserProfileController {

    private final UserService userService;

    //Actualizar el perfil del usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDTO> updateProfile(@PathVariable Integer id,@Valid @RequestBody UserProfileDTO userProfileDTO) {
        UserProfileDTO updatedProfile = userService.updateUserProfile(id, userProfileDTO);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    //Obtener el perfil de un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable Integer id) {
        UserProfileDTO userProfile = userService.getUserProfileById(id);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

}
