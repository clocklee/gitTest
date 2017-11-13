package com.mis.infosys.persist.repositories.Dao.records;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.infosys.persist.entities.records.RecordCode;

public interface IRecordDao extends JpaRepository<RecordCode,Long>{

}
