package com.mis.infosys.persist.repositories.Dao.rbac;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.infosys.persist.entities.rbac.PassProfile;

public interface IPassProfileDao extends JpaRepository<PassProfile,Long>{

}
