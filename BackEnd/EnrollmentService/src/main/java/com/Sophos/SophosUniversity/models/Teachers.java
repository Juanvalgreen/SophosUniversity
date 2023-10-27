package com.Sophos.SophosUniversity.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teachers {

    private Long teacher_id;
    private String teacher_full_name;
    private String max_degree;
    private Integer experience_years;
}
