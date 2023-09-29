package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.entities.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAllStudents() throws Exception;

    Student getStudentById(Long id) throws Exception;

    List<Student> getMultipleStudentsById(Iterable<Long> MultipleId) throws Exception;

    String updateStudent(Student student) throws Exception;

    String addNewStudent(Student student) throws Exception;

    String deleteStudent(Long id);
}
