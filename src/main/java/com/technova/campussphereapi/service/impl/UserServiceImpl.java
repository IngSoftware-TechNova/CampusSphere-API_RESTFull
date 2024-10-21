package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.AuthResponseDTO;
import com.technova.campussphereapi.dto.LoginDTO;
import com.technova.campussphereapi.dto.UserProfileDTO;
import com.technova.campussphereapi.dto.UserRegistrationDTO;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.exception.RoleNotFoundExeption;
import com.technova.campussphereapi.mapper.UserMapper;
import com.technova.campussphereapi.model.entity.Role;
import com.technova.campussphereapi.model.entity.Student;
import com.technova.campussphereapi.model.entity.User;
import com.technova.campussphereapi.model.enums.ERole;
import com.technova.campussphereapi.repository.RoleRepository;
import com.technova.campussphereapi.repository.StudentRepository;
import com.technova.campussphereapi.repository.UserRepository;
import com.technova.campussphereapi.security.TokenProvider;
import com.technova.campussphereapi.security.UserPrincipal;
import com.technova.campussphereapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Transactional
    @Override
    public UserProfileDTO registerStudent(UserRegistrationDTO registrationDTO) {
        return registerUserWithRole(registrationDTO, ERole.STUDENT);
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        // Autenticar al usuario utilizando AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        // Una vez autenticado, el objeto authentication contiene la informacion del usuario autenticado
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();

        String token = tokenProvider.createAccessToken(authentication);

        AuthResponseDTO responseDTO = userMapper.toAuthResponseDTO(user, token);
        return responseDTO;
    }

    @Transactional
    @Override
    public UserProfileDTO updateUserProfile(Integer id, UserProfileDTO userProfileDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));

        //Verificar si ya existe un estudiante con el mismo nombre y apellido (excepto el usuario actual)
        boolean existsAsStudent = studentRepository.existsByFirstNameAndLastNameAndUserIdNot(
                userProfileDTO.getFirstName(), userProfileDTO.getLastName(), id);

        if (existsAsStudent) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo nombre y apellido");
        }

        if(user.getStudent()!=null) {
            user.getStudent().setFirstName(userProfileDTO.getFirstName());
            user.getStudent().setLastName(userProfileDTO.getLastName());
            user.getStudent().setCareer(userProfileDTO.getCareer());
            user.getStudent().setUpdatedAt(LocalDateTime.now());
        }

        User updatedUser = userRepository.save(user);

        return userMapper.toUserProfileDTO(updatedUser);
    }

    @Transactional(readOnly = true)
    @Override
    public UserProfileDTO getUserProfileById(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado"));

        return userMapper.toUserProfileDTO(user);
    }

    private UserProfileDTO registerUserWithRole(UserRegistrationDTO registrationDTO, ERole roleEnum) {
        //Verificar si el email ya esta registradoo o si ya existe un usuario con el mismo nombre y apellido
        boolean existsByEmail = userRepository.existsByEmail(registrationDTO.getEmail());
        boolean existsAsStudent = studentRepository.existsByFirstNameAndLastName(registrationDTO.getFirstName(), registrationDTO.getLastName());

        if (existsByEmail) {
            throw new IllegalArgumentException("El Email ya esta registrado");
        }

        if (existsAsStudent) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo nombre y apellido");
        }

        Role role = roleRepository.findByName(roleEnum)
                .orElseThrow(() -> new RoleNotFoundExeption("Error: Role is not found"));

        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        User user = userMapper.toUserEntity(registrationDTO);
        user.setRole(role);

        if (roleEnum == ERole.STUDENT) {
            Student student = new Student();
            student.setFirstName(registrationDTO.getFirstName());
            student.setLastName(registrationDTO.getLastName());
            student.setCareer(registrationDTO.getCareer());
            student.setCreatedAt(LocalDateTime.now());
            student.setUser(user);
            user.setStudent(student);
        }

        User savedUser = userRepository.save(user);

        return userMapper.toUserProfileDTO(savedUser);
    }
}
