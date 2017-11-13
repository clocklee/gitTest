package com.mis.infosys.persist.entities.rbac;
import javax.persistence.*;
import lombok.*;
@Entity
@Table(name="passprof")
@ToString
@Data
@EqualsAndHashCode
public class PassProfile {
	
	@Id
	@GeneratedValue
	private Long usrid;
	@Column(name="prof")
	private String prof;
	@Column(name="islogin")
	private boolean islogin;
	@Column(name="lastlogintime")
	private String lastlogintime;
	@Column(name="lastloginaddress")
	private String lastloginaddress;
	@Column(name="lastsession")
	private String session;

}
