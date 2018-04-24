package org.enterpriseNetwork.dao.employee;

import org.apache.ibatis.annotations.Param;
import org.enterpriseNetwork.dao.StringBaseDao;
import org.enterpriseNetwork.model.Employee;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public interface EmployeeDao extends StringBaseDao<Employee>{

	Employee getByNo(String worker_no);

	String getResponsibleEnterprise(String employeeId);

	String getResponsibleProducts(String employeeId);

	String getColleagues(String employeeId);

	String createNewColleague(@Param("employeeId")String employeeId, @Param("colleagueId")String colleagueId);

}
