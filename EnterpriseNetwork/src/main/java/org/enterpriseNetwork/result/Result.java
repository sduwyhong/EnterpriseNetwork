package org.enterpriseNetwork.result;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;

/**
 * @author wyhong
 * @date 2018-4-24
 */
@Data
public class Result {

	public static final String OK;
	public static final String BAD_PARAMS;
	public static final String PERMISSION_DENIED;
	
	static{
		OK = JSONObject.toJSONString(new Result());
		Result result = new Result();
		result.setStatus(400);
		result.setMessage("参数有误！");
		BAD_PARAMS = JSONObject.toJSONString(result);
		result.setStatus(403);
		result.setMessage("permission denied");
		PERMISSION_DENIED = JSONObject.toJSONString(result);
	}
	
	private int status = 200;
	private String message = "ok";
	private Object object;
	
}
