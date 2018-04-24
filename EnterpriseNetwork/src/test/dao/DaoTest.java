package dao;

import org.enterpriseNetwork.dao.admin.AdminDao;
import org.enterpriseNetwork.dao.employee.EmployeeDao;
import org.enterpriseNetwork.dao.enterprise.EnterpriseDao;
import org.enterpriseNetwork.dao.product.ProductDao;
import org.enterpriseNetwork.model.Enterprise;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wyhong
 * @date 2018-4-24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-db.xml")
public class DaoTest {

	@Autowired
	EnterpriseDao enterpriseDao;
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	AdminDao adminDao;
	@Autowired
	ProductDao productDao;
	
	@Test
	public void test(){
		Enterprise enterprise = new Enterprise();
		enterprise.setName("深圳鸿飞广告有限公司");
		enterprise.setDescription("广告媒体");
		enterprise.setAddress("深圳市龙岗区水岸新都");
		enterpriseDao.insert(enterprise);
	}
	
}
