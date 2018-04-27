package org.enterpriseNetwork.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.enterpriseNetwork.VO.Corporation;
import org.enterpriseNetwork.dao.admin.AdminDao;
import org.enterpriseNetwork.dao.employee.EmployeeDao;
import org.enterpriseNetwork.dao.enterprise.EnterpriseDao;
import org.enterpriseNetwork.model.Admin;
import org.enterpriseNetwork.model.Employee;
import org.enterpriseNetwork.model.Enterprise;
import org.enterpriseNetwork.model.Product;
import org.enterpriseNetwork.result.Result;
import org.enterpriseNetwork.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	EnterpriseDao enterpriseDao;
	
	@Override
	public String register(Admin admin) {
		Admin _admin = adminDao.getByNo(admin.getAdmin_no(), admin.getEnterprise_id());
		if(_admin != null) {
			Result result = new Result();
			result.setStatus(400);
			result.setMessage("管理员工号已经存在！");
			return JSONObject.toJSONString(result);
		}
		Validator validator = new Validator();
		List<ConstraintViolation> errors = validator.validate(admin);
		if(!errors.isEmpty()) {
			return Result.BAD_PARAMS;
		}
		admin.setId(UUID.randomUUID().toString().replace("-", ""));
		adminDao.insert(admin);
		return Result.OK;
	}

	@Override
	public String login(int enterpriseId, String admin_no, String password,
			HttpServletRequest request, HttpServletResponse response) {
		Admin admin = adminDao.getByNo(admin_no, enterpriseId);
		if(admin == null || !admin.getPassword().equals(password)) {
			Result result = new Result();
			result.setStatus(400);
			result.setMessage("用户名或密码有误！");
			return JSONObject.toJSONString(result);
		}
		//本机ajax不做session跨域管理
		HttpSession session = request.getSession();
		session.setAttribute("admin", admin_no);
		Cookie cookie = null;
		try {
			cookie = new Cookie("admin", admin.getId()+":"+URLEncoder.encode(admin.getName(),"UTF-8")+":"+admin.getEnterprise_id());
			cookie.setPath("/");
			cookie.setMaxAge(60*30);
		} catch (UnsupportedEncodingException e) {
			System.out.println("URL编码不支持，请检查login()");
		}
		response.addCookie(cookie);
		return Result.OK;
	}
	
	@Override
	public String addEmployee(Employee employee) {
		Employee emp = employeeDao.getByNo(employee.getWorker_no(), employee.getEnterprise_id());
		if(emp != null) {
			Result result = new Result();
			result.setStatus(400);
			result.setMessage("工号已经存在！");
			return JSONObject.toJSONString(result);
		}
		Validator validator = new Validator();
		List<ConstraintViolation> errors = validator.validate(employee);
		if(!errors.isEmpty()) {
			return Result.BAD_PARAMS;
		}
		employee.setId(UUID.randomUUID().toString().replace("-", ""));
		employeeDao.insert(employee);
		return Result.OK;
	}

	@Override
	public String deleteEmployee(String employeeId) {
		adminDao.deleteEmployee(employeeId);
		return Result.OK;
	}

	@Override
	public String addProduct(Product product) {
		Validator validator = new Validator();
		List<ConstraintViolation> errors = validator.validate(product);
		if(!errors.isEmpty()) {
			return Result.BAD_PARAMS;
		}
		adminDao.addProduct(product);
		return Result.OK;
	}

	@Override
	public String deleteProduct(int productId) {
		adminDao.deleteProduct(productId);
		adminDao.deleteAllCompositionRelation(productId);
		return Result.OK;
	}

	@Override
	public String addComposition(int productId, int compositionId) {
		adminDao.addComposition(productId, compositionId);
		return Result.OK;
	}

	@Override
	public String deleteComposition(int productId, int compositionId) {
		adminDao.deleteComposition(productId, compositionId);
		return Result.OK;
	}

	@Override
	public String delegateEnterprise(String employeeId, int enterpriseId) {
		adminDao.delegateEnterprise(employeeId, enterpriseId);
		return Result.OK;
	}

	@Override
	public String cancelDelegationOfEnterprise(String employeeId,
			int enterpriseId) {
		adminDao.cancelDelegationOfEnterprise(employeeId, enterpriseId);
		return Result.OK;
	}

	@Override
	public String delegateProduct(String employeeId, int productId) {
		adminDao.delegateProduct(employeeId,productId);
		return Result.OK;
	}

	@Override
	public String cancelDelegationOfProduct(String employeeId, int productId) {
		adminDao.cancelDelegationOfProduct(employeeId,productId);
		return Result.OK;
	}

	@Override
	public String corporate(Corporation corporation) {
		adminDao.corporate(corporation);
		return Result.OK;
	}

	@Override
	public String cancelCorporation(int enterpriseId, int partnerId) {
		adminDao.cancelCorporation(enterpriseId, partnerId);
		String employeeId = adminDao.getEmployeeIdByCorporatioin(enterpriseId, partnerId);
		adminDao.cancelDelegationOfEnterprise(employeeId, partnerId);
		return Result.OK;
	}

	@Override
	public String getEmployees(int enterpriseId) {
		Result result = new Result();
		result.setObject(adminDao.getEmployees(enterpriseId));
		return JSONObject.toJSONString(result);
	}

	@Override
	public String getProducts(int enterpriseId) {
		Result result = new Result();
		result.setObject(adminDao.getProducts(enterpriseId));
		return JSONObject.toJSONString(result);
	}

	@Override
	public String getCompositions(int enterpriseId) {
		Result result = new Result();
		result.setObject(adminDao.getCompositions(enterpriseId));
		return JSONObject.toJSONString(result);
	}

	@Override
	public String getEnterpriseDelegations(int enterpriseId) {
		Result result = new Result();
		result.setObject(adminDao.getEnterpriseDelegations(enterpriseId));
		return JSONObject.toJSONString(result);
	}

	@Override
	public String getProductDelegations(int enterpriseId) {
		Result result = new Result();
		result.setObject(adminDao.getProductDelegations(enterpriseId));
		return JSONObject.toJSONString(result);
	}

	@Override
	public String getCorporations(int enterpriseId) {
		Result result = new Result();
		List<Corporation> corporations = adminDao.getCorporations(enterpriseId);
		for (Corporation corporation : corporations) {
			Employee employee = adminDao.getInCharge(enterpriseId, corporation.getEnterprise_id_2());
			if(employee != null){
				corporation.setEmployee_id(employee.getId());
				corporation.setEmployee_name(employee.getName());
			}
		}
		result.setObject(corporations);
		return JSONObject.toJSONString(result);
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("admin")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				break;
			}
		}
		request.getSession().invalidate();
	}

	@Override
	public String getEnterprises() {
		Result result = new Result();
		result.setObject(adminDao.getEnterprises());
		return JSONObject.toJSONString(result);
	}

	@Override
	public String getEnterpriseInfo(int enterpriseId) {
		Result result = new Result();
		result.setObject(enterpriseDao.get(enterpriseId));
		return JSONObject.toJSONString(result);
	}

	@Override
	public String modifyEnterprise(Enterprise enterprise) {
		enterpriseDao.update(enterprise);
		return Result.OK;
	}

	@Override
	public String getPretentialCompositions(int enterpriseId) {
		Result result = new Result();
		result.setObject(adminDao.getPretentialCompositions(enterpriseId));
		return JSONObject.toJSONString(result);
	}

	@Override
	public String getPretentialCorporations(int enterpriseId) {
		Result result = new Result();
		List<Enterprise> pretentialCorporations = adminDao.getPretentialCorporations(enterpriseId);
		if(pretentialCorporations.size() > 0) result.setObject(pretentialCorporations);
		else result.setObject(adminDao.getOtherEnterprises(enterpriseId));
		return JSONObject.toJSONString(result);
	}

}
