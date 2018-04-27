package org.enterpriseNetwork.VO;

import lombok.Data;

/**
 * @author wyhong
 * @date 2018-4-27
 */
@Data
public class ProductVO {
	private int id;
	private String name;
	private String description;
	private int enterprise_id;
	
	private String employee_name;
	private String employee_id;
}
