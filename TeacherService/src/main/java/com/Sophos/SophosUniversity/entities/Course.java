package com.Sophos.SophosUniversity.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "courses")
@Getter @Setter
@ToString
@EqualsAndHashCode
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_id;
    private Integer teacher_id;
    private String course_name;
    private Integer amount_credits;
    private Integer available_places;
    private Integer course_student_monitor_id;

}
