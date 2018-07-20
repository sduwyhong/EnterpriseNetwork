package org.enterpriseNetwork.controller;

import org.enterpriseNetwork.model.Enterprise;
import org.enterpriseNetwork.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.ApiParam;

/**
 * @author wyhong
 * @date 2018-4-24
 */
@Controller
@RequestMapping("/enterprise")
public class EnterpriseAction {

	private final String JSON = "application/json;charset=utf-8";
	
	@Autowired
	EnterpriseService enterpriseService;
	
	@ResponseBody
	@RequestMapping(value="/",method=RequestMethod.POST,produces=JSON)
	public String register(@ApiParam("id可选") Enterprise enterprise){
		return enterpriseService.register(enterprise);
	}
	
	@ResponseBody
	@RequestMapping(value="/",method=RequestMethod.GET,produces=JSON)
	public String getEnterprises(){
		return enterpriseService.getAll();
	}
	
	@ResponseBody
	@RequestMapping(value="/name/{enterpriseId}",method=RequestMethod.GET,produces=JSON)
	public String getName(@PathVariable("enterpriseId")int enterpriseId){
		return enterpriseService.getName(enterpriseId);
	}
}
