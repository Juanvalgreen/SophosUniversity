package com.Sophos.SophosUniversity.dtos;

import com.Sophos.SophosUniversity.entities.Prerequisite;
import com.Sophos.SophosUniversity.models.Course;
import lombok.Data;

@Data
public class prerequisiteDTO {

    private Long prerequisite_id;
    private Long course_id;
    private Course course_details;
    private Long prerequisite_course_id;
    private Course course_prerequisite_details;

    public prerequisiteDTO(Prerequisite prerequisteInfo, Course course, Course coursePrerequisite){

        this.prerequisite_id =  prerequisteInfo.getPrerequisite_id();
        this.course_id = prerequisteInfo.getCourse_id();
        this.course_details = course;
        this.prerequisite_course_id = prerequisteInfo.getPrerequisite_course_id();
        this.course_prerequisite_details = coursePrerequisite;



    }

}
