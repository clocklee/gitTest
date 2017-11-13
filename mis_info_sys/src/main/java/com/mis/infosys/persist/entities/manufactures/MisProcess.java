package com.mis.infosys.persist.entities.manufactures;

import java.util.List;

import javax.persistence.*;

import com.mis.infosys.persist.entities.rbac.User;

import lombok.*;

@Data
@Entity
@Table(name="process")
@ToString
@EqualsAndHashCode
public class MisProcess {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@ManyToMany
	private List<Technical> technicals;	
	@Column(name = "isLock")
	private boolean islock;
	
	@OneToOne//(mappedBy="usr")
	//@JoinColumn(name = "usr_id")
	private User createBy;
	@Column(name = "createtime")
	private String createTime;	
	
	@Column(name="ratio")
	private double ratio;
	
	public Produce MakeProduce()
	{
		Produce produce = new Produce();
		produce.setMisprocess(this);		
		return produce;
	}
	
}
