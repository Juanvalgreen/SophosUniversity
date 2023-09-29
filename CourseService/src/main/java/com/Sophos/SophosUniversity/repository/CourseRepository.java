package com.Sophos.SophosUniversity.repository;

import  com.Sophos.SophosUniversity.entities.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.teacher_id = :teacherId")
    List<Course> findAllCoursesByTeacherId(@Param("teacherId") Long teacherId);

    @Query("SELECT c FROM Course  c WHERE c.course_student_monitor_id = :monitor_id")
    List<Course> findAllCoursesByMonitorId(@Param("monitor_id") Long monitor_id);



}
