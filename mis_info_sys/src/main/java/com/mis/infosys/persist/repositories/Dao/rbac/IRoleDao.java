package com.mis.infosys.persist.repositories.Dao.rbac;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.infosys.persist.entities.rbac.URole;

public interface IRoleDao extends JpaRepository<URole,Long>{

}
