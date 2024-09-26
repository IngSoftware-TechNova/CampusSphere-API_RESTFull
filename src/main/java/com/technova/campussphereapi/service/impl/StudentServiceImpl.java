package com.technova.campussphereapi.service.impl;

import com.technova.campussphereapi.dto.StudentProfileDTO;
import com.technova.campussphereapi.dto.StudentRegistrationDTO;
import com.technova.campussphereapi.exception.BadRequestException;
import com.technova.campussphereapi.exception.ResourceNotFoundException;
import com.technova.campussphereapi.mapper.EventMapper;
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
    public StudentRegistrationDTO create(StudentRegistrationDTO studentRegistrationDTO) {
        studentRepository.findByEmail(studentRegistrationDTO.getEmail())
                .ifPresent(existingStudent ->{
                    throw new BadRequestException("El autor ya existe con el mismo email");
                });

        Student student = studentMapper.toEntity(studentRegistrationDTO);
        student.setCreatedAt(LocalDateTime.now());
        student = studentRepository.save(student);
        return studentMapper.toDTO(student);

    }

    //Busqueda por id del estudiante
    @Transactional
    @Override
    public StudentProfileDTO findById(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El estudiante con ID "+id+" no fue encontrado"));
        return studentMapper.toDTOs(student);
    }

    @Transactional
    @Override
    public StudentRegistrationDTO update(Integer id, StudentRegistrationDTO studentRegistrationDTO) {
        Student studentFromDB = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El estudiante con ID " + id + " no fue encontrado"));


        studentRepository.findByEmail(studentRegistrationDTO.getEmail())
                .filter(existingAuthor -> !existingAuthor.getId().equals(id))
                .ifPresent(existingAuthor -> {
                    throw new BadRequestException("Ya existe un autor con el mismo nombre y apellido");
                });

        // Actualizar los campos
        studentFromDB.setName(studentRegistrationDTO.getName());
        studentFromDB.setEmail(studentRegistrationDTO.getEmail());
        studentFromDB.setUpdatedAt(LocalDateTime.now());
        studentFromDB.setCareer(studentRegistrationDTO.getCareer());
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
