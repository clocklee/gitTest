package com.mis.infosys.persist.entities.manufactures;
import lombok.*;

import java.util.List;

import javax.persistence.*;

import com.mis.infosys.persist.entities.rbac.User;
import com.mis.infosys.persist.entities.records.RecordCode;

/***
 * the manufacture's produces
 * 
 * @author admin
 *
 */
@Data
@Entity
@Table(name = "produce")
@ToString
@EqualsAndHashCode
public class Produce {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private User usr;
	@ManyToOne
	//@JoinColumn(name="process_id")
	private MisProcess misprocess;
	@Column(name="ispass")
	private boolean ispass;
	@Column(name="descript")
	private String descript;
	@OneToMany
	//@JoinColumn(name="produce_id")
	private List<RecordCode> recordCode;
}
