package com.Sophos.SophosUniversity.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollment_id;
    private Long student_id;
    private Long course_id;
    private Date enrollment_date;

}
