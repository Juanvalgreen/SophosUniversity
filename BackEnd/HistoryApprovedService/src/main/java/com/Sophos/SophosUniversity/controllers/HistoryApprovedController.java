package com.Sophos.SophosUniversity.controllers;

import com.Sophos.SophosUniversity.dtos.historyApprovedDTO;
import com.Sophos.SophosUniversity.entities.HistoryApproved;
import com.Sophos.SophosUniversity.services.IHistoryApprovedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HistoryApprovedController {

    @Autowired
    private IHistoryApprovedService service;

    @GetMapping("/api/v1/approvedCourses")
    public List<historyApprovedDTO> getAllApprovedCourses() throws Exception {
        return service.getAllApprovedCourses();
    }

    @GetMapping("/api/v1/approvedCourses/{id}/students")
    public List<historyApprovedDTO> getApprovedCoursesByStudentdId(@PathVariable Long id) throws Exception {
        return service.getCoursesApprovedByStudentId(id);
    }

    @PostMapping("/api/v1/approvedCourses")
    public String addNewApprovedCourse(@RequestBody HistoryApproved historyApproved) throws Exception{
        return service.addNewApprovedCourse(historyApproved);
    }


    @PutMapping("/api/v1/approvedCourses")
    public String updateApprovedCourse(@RequestBody HistoryApproved historyApproved) throws Exception{
        return service.updateApprovedCourse(historyApproved);
    }


    @DeleteMapping("/api/v1/approvedCourses/{id}")
    public String deleteApprovedCourse(@PathVariable Long id) throws Exception{
        return service.deleteApprovedCourse(id);
    }








}
