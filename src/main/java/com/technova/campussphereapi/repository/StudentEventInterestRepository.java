package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.model.entity.StudentEventInterest;
import com.technova.campussphereapi.model.entity.StudentEventInterestPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentEventInterestRepository extends JpaRepository<StudentEventInterest, StudentEventInterestPK> {
    @Modifying
    @Query(value = "INSERT INTO event_participant (id_event, id_participant) " + "VALUES (:idEvent, :idParticipant)", nativeQuery = true)

    StudentEventInterest insertEventParticipant(@Param("idEvent") Integer idEvent,
                              @Param("idParticipant") Integer idParticipant);
}
