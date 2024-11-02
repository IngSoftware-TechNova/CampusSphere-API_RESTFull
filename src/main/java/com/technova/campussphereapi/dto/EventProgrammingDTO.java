package com.technova.campussphereapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventProgrammingDTO {
    private Integer eventId;
    private Integer scheduleId;

    @JsonFormat(pattern = "dd/MM/yy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yy")
    private LocalDate endDate;

    // Información adicional del evento
    private String eventName;
    private String eventDescription;

    // Información adicional del horario
    @JsonFormat(pattern = "HH:mm")
    private LocalTime scheduleStartHour;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime scheduleEndHour;

    private String scheduleDescription;
}
