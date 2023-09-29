package com.Sophos.SophosUniversity.repository;


import com.Sophos.SophosUniversity.entities.Prerequisite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrerequisiteRepository extends CrudRepository<Prerequisite, Long> {
    @Query("SELECT p FROM Prerequisite p WHERE p.course_id = :courseId")
    List<Prerequisite> findAllPrerequisitesByCourseId(@Param("courseId") Long courseId);

}
