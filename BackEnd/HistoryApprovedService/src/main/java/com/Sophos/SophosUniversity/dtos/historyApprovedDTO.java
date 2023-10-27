package com.Sophos.SophosUniversity.dtos;

import com.Sophos.SophosUniversity.entities.HistoryApproved;
import com.Sophos.SophosUniversity.models.Course;
import com.Sophos.SophosUniversity.models.Students;
import lombok.Data;

@Data
public class historyApprovedDTO {

    private Long history_courses_id;
    private Long student_id;
    private Students student_details;
    private Long course_id;
    private Course course_details;


    public historyApprovedDTO(HistoryApproved historyApproved, Course course, Students student) {

        this.history_courses_id = historyApproved.getHistory_courses_id();
        this.student_id = historyApproved.getStudent_id();
        this.student_details = student;
        this.course_id = historyApproved.getCourse_id();
        this.course_details = course;




    }
}
