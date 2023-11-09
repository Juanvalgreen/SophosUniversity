package com.Sophos.SophosUniversity.controllers;

import com.Sophos.SophosUniversity.dtos.courseDTO;
import com.Sophos.SophosUniversity.entities.Course;
import com.Sophos.SophosUniversity.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CourseController {

    @Autowired
    private ICourseService service;

    @GetMapping("/api/v1/courses")
    public List<courseDTO> getAllCourses() throws Exception {

        return service.getAllCourses();

    }

    @GetMapping("/api/v1/courses/id/{searchId}")
    public List<courseDTO> searchAllCoursesById(@PathVariable Long searchId) throws Exception {
        return service.searchAllCoursesById(searchId);
    }


    @GetMapping("/api/v1/courses/name/{searchName}")
    public List<courseDTO> searchAllCoursesByName(@PathVariable String searchName) throws Exception {
        return service.searchAllCoursesByName(searchName);
    }


    @GetMapping("/api/v1/courses/{id}")
    public courseDTO getCourseById(@PathVariable Long id) throws Exception {

        return service.getCourseById(id);

    }

    @GetMapping("/api/v1/courses/multiple")
    public List<courseDTO> getMultipleCoursesByid(@RequestBody Iterable<Long> id) throws Exception {
        return service.getMultipleCourseById(id);
    }


    @GetMapping("/api/v1/courses/{teacherId}/teachers")
    public List<courseDTO> getCoursesByTeacherId(@PathVariable Long teacherId) throws Exception{
        return service.getCoursesByTeacherId(teacherId);
    }

    @GetMapping("/api/v1/courses/{monitorId}/students")
    public List<courseDTO> getCoursesByMonitorId(@PathVariable Long monitorId) throws Exception{
        return service.getCoursesByMonitorId(monitorId);
    }

    @DeleteMapping("/api/v1/courses/{id}")
    public String deleteCourse(@PathVariable Long id) throws Exception {

        return service.deleteCourse(id);

    }

    @PostMapping("/api/v1/courses")
    public void addNewCourse(@RequestBody Course course) throws Exception {

        service.addNewCourse(course);

    }

    @PutMapping("/api/v1/courses")
    public void updateCourse(@RequestBody Course course) throws Exception {
        service.updateCourse(course);
    }

}
