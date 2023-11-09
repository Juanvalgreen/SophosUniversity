package com.Sophos.SophosUniversity.controllers;


import com.Sophos.SophosUniversity.dtos.enrollmentDTO;
import com.Sophos.SophosUniversity.entities.Enrollment;
import com.Sophos.SophosUniversity.services.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class EnrollmentController {

    @Autowired
    private IEnrollmentService service;


    @GetMapping("/api/v1/enrollments")
    public List<Enrollment> getAllEnrollments() throws Exception {
       return (List<Enrollment>) service.getAllEnrollments();
    }
    @GetMapping("/api/v1/enrollments/{id}")
    public Enrollment getEnrollmentById(@PathVariable Long id) throws Exception {
        return service.getEnrollmentById(id);
    }

    @GetMapping("/api/v1/enrollments/{id}/students")
    public List<enrollmentDTO> getEnrollmentsByStudentId(@PathVariable Long id) throws Exception{
        return service.getEnrollmentsByStudentId(id);
    }

    @GetMapping("/api/v1/enrollments/{id}/courses")
    public List<enrollmentDTO> getEnrollmentByCourseId(@PathVariable Long id) throws Exception {
        return service.getEnrollmentsByCourseId(id);
    }


    @PostMapping("/api/v1/enrollments")
    public String addNewEnrollment(@RequestBody Enrollment enrollment) throws Exception {
        return service.addNewEnrollment(enrollment);
    }


    @PutMapping("/api/v1/enrollments")
    public String updateEnrollment(@RequestBody Enrollment enrollment) throws Exception {
        return service.updateEnrollment(enrollment);
    }

    @DeleteMapping("api/v1/enrollments/{id}")
    public  String deleteEnrollment(@PathVariable Long id) throws Exception {
        return service.deleteEnrollment(id);
    }



}
