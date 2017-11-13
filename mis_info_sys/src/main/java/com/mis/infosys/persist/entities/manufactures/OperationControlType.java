package com.mis.infosys.persist.entities.manufactures;

import java.util.ArrayList;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Table(name = "operation_control_type")
@Entity
@ToString(exclude = "technicals")
@EqualsAndHashCode
public class OperationControlType {

	public OperationControlType() {
		technicals = new ArrayList<Technical>();
	}

	@Id
	@GeneratedValue()
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "descript")
	private String descript;

	@OneToMany(/*
				 * targetEntity=Technical.class,cascade={javax.persistence.
				 * CascadeType.REMOVE},
				 */ mappedBy = "operationControlType")
	// @Cascade(CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.EXTRA)
	// @JoinColumn(/*table="technical",*/name="technical_id")
	private List<Technical> technicals;
}
