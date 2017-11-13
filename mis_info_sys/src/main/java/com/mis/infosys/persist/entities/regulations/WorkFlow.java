package com.mis.infosys.persist.entities.regulations;
import lombok.*;
import javax.persistence.*;

import com.mis.infosys.persist.entities.rbac.User;
/*@Data
@ToString
@EqualsAndHashCode
@Table(name="workflow")
@Entity*/
@SuppressWarnings("unused")
public class WorkFlow {
	
	private Long id;
	
	private WorkFlow front;
	
	private WorkFlow next;
	
	private String name;
	
	private User controlUsr;
	
	
	
	
	
}
