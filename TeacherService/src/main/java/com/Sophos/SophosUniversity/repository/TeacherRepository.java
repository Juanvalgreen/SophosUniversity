package com.Sophos.SophosUniversity.repository;

import com.Sophos.SophosUniversity.entities.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher,Long> {

}
