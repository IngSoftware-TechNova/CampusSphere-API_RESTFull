package com.technova.campussphereapi.api;

import com.technova.campussphereapi.service.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final PasswordResetTokenService passwordResetTokenService;

    // Enviar correo de restablecimiento de contraseña
    @PostMapping("/sendMail")
    public ResponseEntity<Void> sendPasswordResetMail(@RequestBody String email) throws Exception {
        passwordResetTokenService.createAndSendPasswordResetToken(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Verificar la validez del token de restablecimiento de contraseña
    @GetMapping("/reset/check/{token}")
    public ResponseEntity<Boolean> checkTokenValidity(@PathVariable("token") String token) {
        boolean isValid = passwordResetTokenService.isValidToken(token);
        return new ResponseEntity<>(isValid, HttpStatus.OK);
    }

    // Restablecer la contraseña usando el token
    @PostMapping("/reset/{token}")
    public ResponseEntity<Void> resetPassword(@PathVariable("token") String token, @RequestBody String newPassword) {
        passwordResetTokenService.resetPassword(token, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Eliminar el token de reset password en caso haya pasado el tiempo de resetear la
    @DeleteMapping("/remove/{token}")
    public ResponseEntity<Void> removePassword(@PathVariable("token") String token) {
        passwordResetTokenService.removeResetToken(token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
