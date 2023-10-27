package com.Sophos.SophosUniversity.dtos;

import com.Sophos.SophosUniversity.entities.Enrollment;
import com.Sophos.SophosUniversity.models.Courses;
import com.Sophos.SophosUniversity.models.Students;
import lombok.Data;

import java.util.Date;

@Data
public class enrollmentDTO {

    private Long enrollment_id;
    private Long student_id;
    private Students student_details;
    private Long course_id;
    private Courses course_details;
    private Date enrollment_date;



    public enrollmentDTO(Enrollment enrollment, Students student, Courses course){

        this.enrollment_id = enrollment.getEnrollment_id();
        this.student_id = enrollment.getStudent_id();
        this.student_details = student;
        this.course_id = enrollment.getCourse_id();
        this.course_details = course;
        this.enrollment_date = enrollment.getEnrollment_date();

    }

}
