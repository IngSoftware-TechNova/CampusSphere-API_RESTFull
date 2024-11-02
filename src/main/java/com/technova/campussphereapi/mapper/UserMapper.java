package com.technova.campussphereapi.mapper;

import com.technova.campussphereapi.dto.AuthResponseDTO;
import com.technova.campussphereapi.dto.LoginDTO;
import com.technova.campussphereapi.dto.UserProfileDTO;
import com.technova.campussphereapi.dto.UserRegistrationDTO;
import com.technova.campussphereapi.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public User toUserEntity(UserRegistrationDTO registrationDTO) {
        return modelMapper.map(registrationDTO, User.class);
    }

    public UserProfileDTO toUserProfileDTO(User user) {
        UserProfileDTO userProfileDTO = modelMapper.map(user, UserProfileDTO.class);

        if(user.getStudent() != null) {
            userProfileDTO.setFirstName(user.getStudent().getFirstName());
            userProfileDTO.setLastName(user.getStudent().getLastName());
            userProfileDTO.setCareer(user.getStudent().getCareer());
        }
        return userProfileDTO;
    }

    //Convertir de LoginDTO a User(cuando se procesa el login)
    public User toUserEntity(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, User.class);
    }

    //Convertir de User a AuthResponseDTO para la respuesta de autenticacion
    public AuthResponseDTO toAuthResponseDTO(User user, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);

        //Obtener el nombre y apellido
        String firstName = (user.getStudent() != null) ? user.getStudent().getFirstName()
                : "Admin";
        String lastName = (user.getStudent() != null) ? user.getStudent().getLastName()
                : "User";

        authResponseDTO.setFirstName(firstName);
        authResponseDTO.setLastName(lastName);

        authResponseDTO.setRole(user.getRole().getName().name());

        return authResponseDTO;
    }
}
