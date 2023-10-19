package com.Sophos.SophosUniversity.dtos;

import com.Sophos.SophosUniversity.entities.Course;
import com.Sophos.SophosUniversity.models.Students;
import com.Sophos.SophosUniversity.models.Teachers;
import lombok.Data;

@Data
public class courseDTO {
    private Long course_id;
    private Teachers teacher_details;
    private String course_name;
    private Integer amount_credits;
    private Integer available_places;
    private Students student_monitor_details;


    public courseDTO(Course course, Teachers teacher,Students student) {
        this.course_id = course.getCourse_id();
        this.teacher_details = teacher;
        this.course_name = course.getCourse_name();
        this.amount_credits = course.getAmount_credits();
        this.available_places= course.getAvailable_places();
        this.student_monitor_details= student;
    }



}
