package com.technova.campussphereapi.service;

import com.technova.campussphereapi.model.entity.PasswordResetToken;

public interface PasswordResetTokenService {

    void createAndSendPasswordResetToken(String email) throws Exception;
    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUserId(Integer userId);
    void removeResetToken(String token);
    boolean isValidToken(String token);
    void resetPassword(String token, String newPassword);

}
