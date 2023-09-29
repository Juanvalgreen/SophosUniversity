package com.Sophos.SophosUniversity.repository;

import com.Sophos.SophosUniversity.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {

}
