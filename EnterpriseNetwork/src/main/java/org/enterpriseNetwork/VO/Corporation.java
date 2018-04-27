package org.enterpriseNetwork.VO;

import lombok.Data;

/**
 * @author wyhong
 * @date 2018-4-24
 */
@Data
public class Corporation {

	private int id;
	private int enterprise_id_1;
	private String enterprise_1_name;
	private int enterprise_id_2;
	private String enterprise_2_name;
	private boolean supply;
	private boolean stock;
	private boolean distribution;
	private String employee_name;
	private String employee_id;
}
