package com.mis.infosys.persist.entities.manufactures;
import lombok.*;

import java.util.List;

import javax.persistence.*;


@Data
@Table(name="manufacture")
@Entity
@ToString
@EqualsAndHashCode
public class Manufacture {
	
	public Manufacture()
	{}
	public Manufacture(Product product)
	{
		this.setProduct(product);
	}
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="sn")
	private String sn;
	
	@ManyToOne
	//@JoinColumn(name="product_id")
	private Product product;
	
	@OneToMany
	//@JoinColumn(name="manufacture_id")
	private List<Produce> producess;
	
}
