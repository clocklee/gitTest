package com.mis.infosys.persist.entities.rbac;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@Entity
@EqualsAndHashCode
@ToString
@Table(name="usrgroup")
public class UGroup {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="name")	
	private String name;
	@Column(name="descript")
	private String descript;
	
	/***
	 * parent group like r&d_dep_1 is swg's parent
	 */
	@ManyToOne
	@JoinColumn(name="parentid")
	private UGroup parent;
	
	/***
	 * group's child like r&d_dep_1 has 6 child_groups,like SWG,HWG,EWG,OG,TG,MG
	 */
	@OneToMany
	@JoinColumn(name="parentid")
	private List<UGroup> childs;
	/***
	 * the group's roles like SWG has software engineer,software group leader;
	 */
	@ManyToMany
	private List<URole> roles;
	/***
	 * the group's user like SWG has 5 users, they are LW,JWP,SWF,ZY,GTT
	 */
	@ManyToMany
	private List<User> usrs;
	

}
