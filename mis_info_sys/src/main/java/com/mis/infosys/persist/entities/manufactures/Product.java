package com.mis.infosys.persist.entities.manufactures;
import java.util.List;

import javax.persistence.*;

import com.mis.infosys.persist.entities.rbac.User;

import lombok.*;


/***
 * this class is product;*
 * 
 * @category
 * how to design the product manufacture log?
 * product->manufacture->process->produces->main_log 
 * 
 * @author admin
 * 
 */

@Data
@ToString
@EqualsAndHashCode
@Table(name="product")
@Entity
public class Product {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="bomid")
	private String bomid;
	@Column(name="name")
	private String name;
	@Column(name="fullname")
	private String fullname;
	@Column(name="shortname")
	private String shortname;
	@Column(name="customername")
	private String customername;
	@OneToMany
	private List<MisProcess> processes;
	
	@Column(name="version")
	private String version;
	
	@Column(name="ratio")
	private double ratio;
	
	@ManyToOne
	private User createBy;
	
	public Manufacture MakeManufacture()
	{
		Manufacture mf = new Manufacture(this);			
		return mf;
	}
	
}
