package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.dtos.enrollmentDTO;
import com.Sophos.SophosUniversity.entities.Enrollment;

import java.util.List;

public interface IEnrollmentService {
    List<Enrollment> getAllEnrollments() throws Exception;

    Enrollment getEnrollmentById(Long id) throws Exception;

    List<enrollmentDTO> getEnrollmentsByCourseId(Long id) throws Exception;

    List<enrollmentDTO> getEnrollmentsByStudentId(Long id) throws Exception;

    String addNewEnrollment(Enrollment enrollment) throws Exception;

    String updateEnrollment(Enrollment enrollment) throws Exception;

    String deleteEnrollment(Long id) throws Exception;
}
