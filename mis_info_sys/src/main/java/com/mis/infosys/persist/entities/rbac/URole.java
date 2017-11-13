package com.mis.infosys.persist.entities.rbac;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mis.infosys.persist.entities.manufactures.Technical;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Table(name="role")
@ToString
@EqualsAndHashCode
@Entity
public class URole {
	
	/**
	 * role's id
	 */
	@Id
	@GeneratedValue
	private Long id;
	/***
	 * role's name
	 */
	@Column(name="name")
	private String name;	
	
	@Column(name="descript")
	private String descript;
	
	/***
	 * the role's authority
	 */
	@ManyToOne
	private Authority authority;

	/***
	 * the role's technical
	 */
	@ManyToMany
	private List<Technical> tehcnicals;	

}
