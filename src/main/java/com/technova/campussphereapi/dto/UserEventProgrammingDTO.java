package com.technova.campussphereapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEventProgrammingDTO {
    private String inscriptionId;
    private String inscriptionStatus;
    private String studentName;
    private String total;                   // Cambiado temporalmente a String
    private String createdAt;
    private List<EventItem> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventItem {
        private String eventId;
        private String eventName;
        private String eventDescription;
        private String scheduleStart;
        private String scheduleEnd;
        private String eventStartDate;
        private String eventEndDate;
    }
}
