package com.mis.infosys.persist.entities;




import com.google.gson.annotations.Expose;

import lombok.*;

@Data
@lombok.ToString(exclude={"password"})//,includeFieldNames = false)

public class Dog {
	
	private Long id;
	private String username;
	@Expose(serialize = false)//, deserialize = false)
	private String password;
}
