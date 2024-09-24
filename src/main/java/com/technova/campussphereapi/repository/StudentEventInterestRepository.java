package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.StudentEventInterest;
import com.technova.campussphereapi.model.entity.StudentEventInterestPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEventInterestRepository extends JpaRepository<StudentEventInterest, StudentEventInterestPK> {

}
