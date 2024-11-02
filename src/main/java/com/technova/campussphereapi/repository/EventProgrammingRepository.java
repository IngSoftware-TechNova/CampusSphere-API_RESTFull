package com.technova.campussphereapi.repository;

import com.technova.campussphereapi.dto.UserEventProgrammingDTO;
import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.model.entity.EventProgramming;
import com.technova.campussphereapi.model.entity.EventProgrammingPK;
import com.technova.campussphereapi.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventProgrammingRepository extends JpaRepository<EventProgramming, EventProgrammingPK> {
    List<EventProgramming> findByEvent(Event event);
    List<EventProgramming> findBySchedule(Schedule schedule);
    @Query(value = "SELECT * FROM get_user_event_programming(:studentId)", nativeQuery = true)
    List<Object[]> getUserEventProgramming(@Param("studentId")Integer studentId);

}
