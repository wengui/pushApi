package com.push.pushApi.action.checkerDeviceTokenAction;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.push.bean.gen.NanoCheckerDeviceToken;
import com.push.dao.readdao.NanoCheckerDeviceTokenReadMapper;
import com.push.dao.writedao.NanoCheckerDeviceTokenWriteMapper;
import com.push.pushApi.action.ApiBaseAction;
import com.push.pushApi.action.checkerRefAction.CheckerRefOutputBean;

import net.sf.json.JSONObject;

/**
 * 更新两个参数的结果依赖
 * @author yuting
 *
 */
public class UpdateDeviceToken extends ApiBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1952672370753974717L;
	@Autowired
	private NanoCheckerDeviceTokenReadMapper nanoCheckerDeviceTokenReadMapper;
	@Autowired
	private NanoCheckerDeviceTokenWriteMapper nanoCheckerDeviceTokenWriteMapper;

	private JSONObject result;

	public String doExec() throws Exception {
		// 处理跨域请求问题
		super.sethttp(ServletActionContext.getResponse());

		// 将前台传来的参数copy到javabean中，方便处理
		// 病人名
		// 测量时间
		HttpServletRequest request = ServletActionContext.getRequest();
		NanoCheckerDeviceToken token = nanoCheckerDeviceTokenReadMapper.selectByDeviceToken(request.getParameter("deviceToken"));
		// 当数据库中没有该记录时，进行插入
		if(token == null){
			// 更新第一条记录的最大值
			NanoCheckerDeviceToken record = new NanoCheckerDeviceToken();
			record.setDevicetoken(request.getParameter("deviceToken"));
			nanoCheckerDeviceTokenWriteMapper.insertSelective(record);
		}
		
		// 将java对象转成json对象
		CheckerRefOutputBean out = new CheckerRefOutputBean();
		out.setStatu("ok");
		// 返回更新状况
		result = JSONObject.fromObject(out);

		return SUCCESS;
	}

	
	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

}
