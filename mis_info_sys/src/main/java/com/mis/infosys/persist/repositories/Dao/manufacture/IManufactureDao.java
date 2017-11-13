package com.mis.infosys.persist.repositories.Dao.manufacture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mis.infosys.persist.entities.manufactures.Manufacture;

public interface IManufactureDao extends JpaRepository<Manufacture,Long>,JpaSpecificationExecutor<Manufacture>{

}
