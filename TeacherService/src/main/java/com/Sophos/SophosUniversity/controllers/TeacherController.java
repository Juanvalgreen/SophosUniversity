package com.Sophos.SophosUniversity.controllers;

import com.Sophos.SophosUniversity.entities.Teacher;
import com.Sophos.SophosUniversity.services.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private ITeacherService service;

    @GetMapping("/api/v1/teachers")
    public List<Teacher> getAllTeachers() throws Exception {
        return service.getAllTeachers();
    }

    @GetMapping("/api/v1/teachers/{id}")
    public Teacher getTeacherById(@PathVariable Long id) throws Exception{
        return service.getTeacherById(id);
    }

    @GetMapping("/api/v1/multipleTeachers")
    public List<Teacher> getMultipleTeachersById(@RequestBody Iterable<Long> id) throws Exception{
        return service.getMultipleTeachersById(id);
    }

    @PostMapping("/api/v1/teachers")
    public String addNewTeacher(@RequestBody Teacher teacher){
        return service.addNewTeacher(teacher);
    }

    @PutMapping("/api/v1/teachers")
    public String updateTeacher(@RequestBody Teacher teacher) throws Exception {
        return service.updateTeacher(teacher);
    }




    @DeleteMapping("/api/v1/teachers/{id}")
    public String deleteTeacher(@PathVariable Long id) throws Exception{
        return service.deleteTeacher(id);
    }






}
