package com.technova.campussphereapi.service.impl;


import com.technova.campussphereapi.model.entity.Comentario;
import com.technova.campussphereapi.repository.ComentarioRepository;
import com.technova.campussphereapi.service.ComentarioService;
//import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; //se utiliza este import para poder utilizar la anotacion @Transactional
// claro ya entiendo profe, pense que era el de jakarta

import java.time.LocalDateTime;
import java.util.List;

//como se habla por aca
// creo que no se puede por micro, por el blackboard si, no se si el anydesk deje hacer eso

@RequiredArgsConstructor
@Service
public class ComentarioServiceImpl implements ComentarioService {
    private final ComentarioRepository comentarioRepository;

    @Override
    public List<Comentario> getAll() {
        return comentarioRepository.findAll();
    }

    @Override
    public Page<Comentario> paginate(Pageable pageable) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Comentario findById(Integer id) {
        //findById se utiliza para buscar por id
        //orElseThrow se utiliza para lanzar una excepcion si no se encuentra el comentario
        return comentarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Comentario not found"));
    }

    @Transactional
    @Override
    public Comentario create(Comentario comentario) {
        comentario.setFechaComentar(LocalDateTime.now());
        return comentarioRepository.save(comentario);
    }

    @Transactional
    @Override
    public Comentario update(Integer id, Comentario updateCategory) {
        Comentario comentarioFromDb = findById(id);
        return comentarioRepository.save(comentarioFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Comentario comentario = findById(id);
        comentarioRepository.delete(comentario);
    }
}

