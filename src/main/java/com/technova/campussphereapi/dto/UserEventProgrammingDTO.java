package com.technova.campussphereapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEventProgrammingDTO {
    private String eventName;
    private String eventDescription;
    private String scheduleStart;
    private String scheduleEnd;
    private String eventStartDate;
    private String eventEndDate;
}
