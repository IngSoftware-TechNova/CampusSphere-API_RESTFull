package com.technova.campussphereapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class StudentEventInterestDTO {
    private Integer studentId;
    private Integer eventId;
    private String eventName;
    private LocalDateTime eventCreatedAt;
    private String eventLocationName;
    private BigDecimal eventPriceAmount;
}
