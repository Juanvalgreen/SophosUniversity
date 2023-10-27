package com.Sophos.SophosUniversity.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prerequisites {
    private Long prerequisite_id;
    private Long course_id;
    private Courses course_details;
    private Long prerequisite_course_id;
    private Courses course_prerequisite_details;
}
