package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.Role;
import com.technova.campussphereapi.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    //Buscar un rol por su nombre
    Optional<Role> findByName(ERole name);
}
