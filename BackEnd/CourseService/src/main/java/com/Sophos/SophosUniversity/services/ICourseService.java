package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.dtos.courseDTO;
import com.Sophos.SophosUniversity.entities.Course;

import java.util.List;

public interface ICourseService {
    List<courseDTO> getAllCourses() throws Exception;

    List<courseDTO> searchAllCoursesById(Long id) throws Exception;

    courseDTO getCourseById(Long id) throws Exception;

    List<courseDTO> searchAllCoursesByName(String name) throws Exception;

    List<courseDTO> getMultipleCourseById(Iterable<Long> MultipleId) throws Exception;

    List<courseDTO> getCoursesByTeacherId(Long teacherId) throws Exception;



    List<courseDTO> getCoursesByMonitorId(Long monitorId) throws Exception;

    String deleteCourse(Long id) throws Exception;
    String addNewCourse(Course course) throws Exception;

    String updateCourse(Course course) throws Exception;
}
