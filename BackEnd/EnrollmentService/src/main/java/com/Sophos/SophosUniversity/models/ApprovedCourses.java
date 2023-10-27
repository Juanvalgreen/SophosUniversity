package com.Sophos.SophosUniversity.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovedCourses {

    private Long history_courses_id;
    private Long student_id;
    private Students student_details;
    private Long course_id;
    private Courses course_details;

}
