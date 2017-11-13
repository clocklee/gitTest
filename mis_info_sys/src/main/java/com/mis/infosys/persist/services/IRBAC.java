package com.mis.infosys.persist.services;

import java.util.List;

import com.mis.infosys.persist.entities.rbac.Usr;

/***
 * user management interface to prepare for SHIRO or spring security
 * @author admin
 *
 */
public interface IRBAC {
	
	boolean addUsr();
	
	boolean updateUsr();
	
	boolean deleteUsr();
	
	List<Usr> getAll();
	
	
	
	
	
	
	Usr getUsr(Long id);

	
	

}
