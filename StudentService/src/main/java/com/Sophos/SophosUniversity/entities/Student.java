package com.Sophos.SophosUniversity.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;
    private Long monitor_course_id;
    private String student_full_name;
    private String faculty;
    private Integer available_credits;
    private Integer enrolled_credits;
}
