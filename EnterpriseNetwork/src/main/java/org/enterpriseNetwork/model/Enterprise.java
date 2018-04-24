package org.enterpriseNetwork.model;

import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.NotBlank;
import lombok.Data;

/**
 * @author wyhong
 * @date 2018-4-24
 */
@Data
public class Enterprise {

	private int id;
	@NotBlank
	@MaxLength(20)
	private String name;
	@NotBlank
	@MaxLength(100)
	private String address;
	@NotBlank
	@MaxLength(500)
	private String description;
	
}
