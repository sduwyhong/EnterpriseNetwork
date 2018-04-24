package org.enterpriseNetwork.model;

import lombok.Data;
import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotBlank;

/**
 * @author wyhong
 * @date 2018-4-24
 */
@Data
public class Employee {

	private String id;
	@NotBlank
	@MaxLength(6)
	private String worker_no;
	@NotBlank
	@MaxLength(32)
	private String password;
	@NotBlank
	@MaxLength(5)
	private String name;
	@NotBlank
	private String gender;
	@Min(18)
	@Max(120)
	private int age;
	@Email
	private String email;
	private int enterprise_id;
	
}
