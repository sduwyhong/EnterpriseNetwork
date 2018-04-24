package org.enterpriseNetwork.dao.employee;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.enterpriseNetwork.dao.StringBaseDao;
import org.enterpriseNetwork.model.Employee;
import org.enterpriseNetwork.model.Enterprise;
import org.enterpriseNetwork.model.Product;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public interface EmployeeDao extends StringBaseDao<Employee>{

	Employee getByNo(String worker_no);

	List<Enterprise> getResponsibleEnterprises(String employeeId);

	List<Product> getResponsibleProducts(String employeeId);

	List<Employee>getColleagues(String employeeId);

	int createNewColleague(@Param("employeeId")String employeeId, @Param("colleagueId")String colleagueId);

	List<Employee> getPretenialColleagues(String employeeId);

}
