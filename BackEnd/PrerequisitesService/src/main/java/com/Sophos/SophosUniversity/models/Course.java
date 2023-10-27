package com.Sophos.SophosUniversity.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private Long course_id;
    private Teachers teacher_details;
    private String course_name;
    private Integer amount_credits;
    private Integer available_places;
    private Students student_monitor_details;


}
