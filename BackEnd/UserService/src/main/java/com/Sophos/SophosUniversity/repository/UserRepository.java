package com.Sophos.SophosUniversity.repository;


import com.Sophos.SophosUniversity.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.user_mail = :userMail")
    List<User> findAllUsersByuserMail(@Param("userMail") String userMail);

}
