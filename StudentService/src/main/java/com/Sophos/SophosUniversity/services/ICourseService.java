package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.entities.Course;

import java.util.List;

public interface ICourseService {
    List<Course> getAllCourses() throws Exception;
    Course getCourseById(Long id) throws Exception;

    List<Course> getMultipleCourseById(Iterable<Long> MultipleId) throws Exception;

    List<Course> getCoursesByTeacherId(Long teacherId) throws Exception;



    List<Course> getCoursesByMonitorId(Long monitorId) throws Exception;

    String deleteCourse(Long id) throws Exception;
    String addNewCourse(Course course) throws Exception;

    String updateCourse(Course course) throws Exception;
}
