package com.technova.campussphereapi.dto;

import com.technova.campussphereapi.model.entity.Event;
import com.technova.campussphereapi.model.entity.Student;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RatingDetailsDTO {

    private Integer id;

    private Integer rate;

    private String eventName;

    private String studentName;

}