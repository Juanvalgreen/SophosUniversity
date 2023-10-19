package com.Sophos.SophosUniversity.repository;

import com.Sophos.SophosUniversity.entities.Enrollment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {

    @Query("SELECT e FROM Enrollment e WHERE e.course_id = :courseId")
    List<Enrollment> findAllEnrollementsByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT e FROM Enrollment e WHERE e.student_id = :studentId")
    List<Enrollment> findAllEnrollementsByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.student_id = :courseId")
    Integer countEnrollmentsByCourseId(@Param ("courseId") Long courseId);

}
