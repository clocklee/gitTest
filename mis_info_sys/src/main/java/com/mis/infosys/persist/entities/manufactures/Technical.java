package com.mis.infosys.persist.entities.manufactures;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Table(name="Technical")
@Entity
@ToString()
@EqualsAndHashCode()
public class Technical {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="descript")
	private String descript;
	@Column(name="use_control")//是否可用
	private boolean useControl;
	@Column(name="per_requesite")//前置条件	
    private boolean perRequesite;

	/**
	 * 
	 * @Attention:It must be use the function of delete( entity.id) not use delete(entity),
	 *  when you properly want to delete the oneToMany entity;
	 */
	@ManyToOne//(fetch=FetchType.LAZY)
	@JoinColumn(/*table="operation_control_type",*/ name="operation_control_type_id")	
	private OperationControlType operationControlType;	

}
