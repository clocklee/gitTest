package com.mis.infosys.persist.repositories.Dao.manufacture;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mis.infosys.persist.entities.manufactures.Product;

public interface IProductDao extends JpaRepository<Product,Long>{

}
