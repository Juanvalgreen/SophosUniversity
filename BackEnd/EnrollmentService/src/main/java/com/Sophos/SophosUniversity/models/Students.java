package com.Sophos.SophosUniversity.models;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Students {

    private Long monitor_course_id;
    private Long student_id;
    private String student_full_name;
    private String faculty;
    private Integer available_credits;
    private Integer enrolled_credits;
}
