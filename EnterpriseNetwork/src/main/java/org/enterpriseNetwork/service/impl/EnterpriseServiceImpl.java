package org.enterpriseNetwork.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.enterpriseNetwork.dao.enterprise.EnterpriseDao;
import org.enterpriseNetwork.model.Enterprise;
import org.enterpriseNetwork.result.Result;
import org.enterpriseNetwork.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

/**
 * @author wyhong
 * @date 2018-4-24
 */
public class EnterpriseServiceImpl implements EnterpriseService {

	@Autowired
	EnterpriseDao enterpriseDao;
	
	@Override
	public String register(Enterprise enterprise) {
		Validator validator = new Validator();
		List<ConstraintViolation> errors = validator.validate(enterprise);
		if(!errors.isEmpty()) {
			return Result.BAD_PARAMS;
		}
		enterpriseDao.insert(enterprise);
		return Result.OK;
	}

	@Override
	public String getName(int enterpriseId) {
		Result result = new Result();
		result.setObject(enterpriseDao.getName(enterpriseId));
		return JSONObject.toJSONString(result);
	}

	@Override
	public String getAll() {
		Result result = new Result();
		result.setObject(enterpriseDao.getAll());
		return JSONObject.toJSONString(result);
	}

}
