package com.Sophos.SophosUniversity.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prerequisites {
    private Long course_id;
    private Long prerequisite_course_id;
}
