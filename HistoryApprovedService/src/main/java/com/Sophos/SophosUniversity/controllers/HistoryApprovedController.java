package com.Sophos.SophosUniversity.controllers;

import com.Sophos.SophosUniversity.entities.HistoryApproved;
import com.Sophos.SophosUniversity.services.IHistoryApprovedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HistoryApprovedController {

    @Autowired
    private IHistoryApprovedService service;

    @GetMapping("/api/v1/approvedCourses")
    public List<HistoryApproved> getAllApporvedCourses() throws Exception {
        return service.getAllApprovedCourses();
    }

    @GetMapping("/api/v1/students/{id}/approvedCourses")
    public List<HistoryApproved> getApprovedCoursesByStudentdId(@PathVariable Long id) throws Exception {
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


    @DeleteMapping("/api/v1/approvedCourses")
    public String deleteApprovedCourse(@PathVariable Long id) throws Exception{
        return service.deleteApprovedCourse(id);
    }








}
