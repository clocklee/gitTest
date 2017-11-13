package com.mis.infosys.persist.services;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;

public interface IService<T,K extends Serializable> {
	
	void save(T t);  
    
    void delete(T t);  
     
    void delete(K k);  
     
    void update(T t);  
     
    T findById(K k);  
     
    Iterable<T> findAll();  
     
    Iterable<T> findAll(Pageable pageable);  

}
