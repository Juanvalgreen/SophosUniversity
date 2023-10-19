package com.Sophos.SophosUniversity.entities;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "history_approved_courses")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class HistoryApproved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long history_courses_id;
    private Long student_id;
    private Long course_id;
}
