package com.mis.infosys.persist.entities.rbac;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name="Authority")
@ToString()
@EqualsAndHashCode()
public class Authority {
	
	@Id
	@GeneratedValue //(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="descript")
	private String descript;
	@Column(name="level")
	private int level;

}
