package dao;

import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
//		Enterprise enterprise = new Enterprise();
//		enterprise.setName("深圳鸿飞广告有限公司");
//		enterprise.setDescription("广告媒体");
//		enterprise.setAddress("深圳市龙岗区水岸新都");
		enterpriseDao.getAll();
	}
	
	public void mybatisInsertDemo() throws Exception{
		//获取配置文件
		InputStream in = DaoTest.class.getClassLoader().getResourceAsStream("mybatis/mybatis.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = sessionFactory.openSession();
		//获取Dao代理对象
		Dao dao = session.getMapper(Dao.class);
		Enterprise enterprise = new Enterprise();
		//略过初始化
		//执行
		dao.insert(enterprise);
		session.close();
	}
	
	public Enterprise mybatisSelectDemo() throws Exception{
		//获取配置文件
		InputStream in = DaoTest.class.getClassLoader().getResourceAsStream("mybatis/mybatis.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		SqlSession session = sessionFactory.openSession();
		//获取Dao代理对象
		Dao dao = session.getMapper(Dao.class);
		//执行
		Enterprise enterprise = dao.get(1);
		session.close();
		return enterprise;
	}
}
