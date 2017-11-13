package com.mis.infosys.persist.repositories.Dao.rbac;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.infosys.persist.entities.rbac.Authority;

public interface IAuthorityDao extends JpaRepository<Authority,Long>{

}
