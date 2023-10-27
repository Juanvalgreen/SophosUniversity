package com.Sophos.SophosUniversity.models;


import com.Sophos.SophosUniversity.dtos.courseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollments {

    private Long enrollment_id;
    private Long student_id;
    private Students student_details;
    private Long course_id;
    private courseDTO course_details;
    private Date enrollment_date;



}
