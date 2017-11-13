package com.mis.infosys.persist.entities.rbac;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mis.infosys.persist.entities.manufactures.Technical;

import lombok.Data;
import lombok.EqualsAndHashCode;
//import lombok.Setter;
import lombok.ToString;


@Data
@Table(name="usr")
@EqualsAndHashCode
@ToString
@Entity
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="username")
	//@Setter
	private String username;
	@Column(name="workid")
	private Long workid;	
	@Column(name="password")
	@JsonIgnore
	private String password;
	@Column(name="phone")
	private String phone;
	@Column(name="mobile")
	private String mobile;
	@Column(name="email1")
	private String email1;
	@Column(name="email2")
	private String email2;
	@Column(name="createTime")
	private String creteTime;
	@Column(name="enable")
	private boolean enable;
	@Column(name="descript")
	private String descript;
	
	@ManyToMany
	private List<Technical> technicals;
	
	@ManyToMany
	private List<UGroup> groups;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}

	@Override
	public String getPassword() {
		
		return this.password;
	}

	@Override
	public String getUsername() {
		
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {		
		return this.enable;
	}
	
}
