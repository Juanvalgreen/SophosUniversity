package com.Sophos.SophosUniversity.entities;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "course_prerequisites")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Prerequisite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prerequisite_id;
    private Long course_id;
    private Long prerequisite_course_id;


}
