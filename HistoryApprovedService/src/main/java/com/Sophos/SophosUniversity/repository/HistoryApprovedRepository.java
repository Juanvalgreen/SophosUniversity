package com.Sophos.SophosUniversity.repository;


import com.Sophos.SophosUniversity.entities.HistoryApproved;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryApprovedRepository extends CrudRepository<HistoryApproved,Long> {

    @Query("SELECT h FROM HistoryApproved h WHERE h.student_id = :studentId")
    List<HistoryApproved> findAllCoursesApprovedByStudentId(@Param("studentId") Long studentId);


}
