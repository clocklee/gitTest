package com.mis.infosys.persist.repositories.Dao.rbac;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mis.infosys.persist.entities.rbac.User;



@Repository
public interface UserDao  extends JpaRepository<User,Long>{

}
