package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {
}
