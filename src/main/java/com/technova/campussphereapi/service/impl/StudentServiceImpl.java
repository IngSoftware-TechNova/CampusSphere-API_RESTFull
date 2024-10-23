package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.UserProfileDTO;
import com.technova.campussphereapi.dto.UserRegistrationDTO;
import com.technova.campussphereapi.exception.BadRequestException;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.StudentMapper;
import com.technova.campussphereapi.model.entity.Student;
import com.technova.campussphereapi.repository.StudentRepository;
import com.technova.campussphereapi.service.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Transactional
    @Override
    public UserRegistrationDTO create(UserRegistrationDTO userRegistrationDTO) {
        studentRepository.findByFirstNameAndLastName(userRegistrationDTO.getFirstName(), userRegistrationDTO.getLastName())
                .ifPresent(existingStudent ->{
                    throw new BadRequestException("El estudiante ya existe con el mismo nombre y apellido");
                });

        Student student = studentMapper.toEntity(userRegistrationDTO);
        student.setCreatedAt(LocalDateTime.now());
        student = studentRepository.save(student);
        return studentMapper.toDTO(student);

    }

    //Busqueda por id del estudiante
    @Transactional
    @Override
    public UserProfileDTO findById(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El estudiante con ID "+id+" no fue encontrado"));
        return studentMapper.toDTOs(student);
    }

    @Transactional
    @Override
    public UserRegistrationDTO update(Integer id, UserRegistrationDTO userRegistrationDTO) {
        Student studentFromDB = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El estudiante con ID " + id + " no fue encontrado"));


        studentRepository.findByFirstNameAndLastName(userRegistrationDTO.getFirstName(), userRegistrationDTO.getLastName())
                .filter(existingAuthor -> !existingAuthor.getId().equals(id))
                .ifPresent(existingAuthor -> {
                    throw new BadRequestException("Ya existe un estudiante con el mismo nombre y apellido");
                });

        // Actualizar los campos
        studentFromDB.setFirstName(userRegistrationDTO.getFirstName());
        studentFromDB.setLastName(userRegistrationDTO.getLastName());
        studentFromDB.setUpdatedAt(LocalDateTime.now());
        studentFromDB.setCareer(userRegistrationDTO.getCareer());
        studentFromDB.setCreatedAt(LocalDateTime.now());

        studentFromDB = studentRepository.save(studentFromDB);
        return studentMapper.toDTO(studentFromDB);

    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El autor con ID " + id + " no fue encontrado"));
        studentRepository.delete(student);
    }
}
