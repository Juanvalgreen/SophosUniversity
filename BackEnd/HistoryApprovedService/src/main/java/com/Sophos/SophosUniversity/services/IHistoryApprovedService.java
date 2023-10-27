package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.dtos.historyApprovedDTO;
import com.Sophos.SophosUniversity.entities.HistoryApproved;

import java.util.List;

public interface IHistoryApprovedService {
    List<historyApprovedDTO> getAllApprovedCourses() throws Exception;

    List<historyApprovedDTO> getCoursesApprovedByStudentId(Long id) throws Exception;

    String addNewApprovedCourse(HistoryApproved historyApproved) throws Exception;

    String updateApprovedCourse(HistoryApproved historyApproved) throws Exception;

    String deleteApprovedCourse(Long id) throws Exception;
}
