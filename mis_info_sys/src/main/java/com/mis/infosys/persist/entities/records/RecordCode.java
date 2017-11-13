package com.mis.infosys.persist.entities.records;

import lombok.*;
import javax.persistence.*;

import com.mis.infosys.persist.entities.manufactures.Product;
import com.mis.infosys.persist.entities.rbac.User;


@Data
@ToString
@Entity
@Table(name="manufacturelog")
@EqualsAndHashCode
public class RecordCode {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Product produce;
	
	@Column(name="errortype")
	private String errortype;
	@Column(name="descript")
	private String descript;
	@Column(name="hassolved")
	private boolean hassolved;
	
	@ManyToOne	
	private User createdBy;	
	@Column(name="createtime")
	private String createtime;
	
	@ManyToOne
	private User solvedBy;
	
	@Column(name="solvedtime")
	private String solvedtime;	
	
}
