package com.push.pushApi.action.checkerRefAction;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.push.constant.CodeConstant;
import com.push.dao.writedao.NanoCheckerRefWriteMapper;
import com.push.pushApi.action.ApiBaseAction;

import net.sf.json.JSONObject;

/**
 * 更新三个参数的结果依赖
 * @author yuting
 *
 */
public class UpdateCheckerRefAllAction extends ApiBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1952672370753974717L;

	@Autowired
	private NanoCheckerRefWriteMapper nanoCheckerRefWriteMapper;

	// 前台对应的参数bean
	private CheckerRefInputBean param = new CheckerRefInputBean();

	private JSONObject result;

	public String doExec() throws Exception {
		// 处理跨域请求问题
		super.sethttp(ServletActionContext.getResponse());

		// 将前台传来的参数copy到javabean中，方便处理
		// 病人名
		// 测量时间
		HttpServletRequest request = ServletActionContext.getRequest();
		BeanUtils.populate(param, request.getParameterMap());

		// 更新第一条记录的最大值
		nanoCheckerRefWriteMapper.updateByMaxValue(param.getItemName(), param.getMin(), CodeConstant.RECORD_MIN, param.getAge());
		 
		// 更新最小值最大值
		nanoCheckerRefWriteMapper.updateByMaxAndMinValue(param.getItemName(), param.getMax(), param.getMin(), CodeConstant.RECORD_MAX_MIN, param.getAge());
		 // 更新第二条记录的最小值
		nanoCheckerRefWriteMapper.updateByMinValue(param.getItemName(), param.getMax(),CodeConstant.RECORD_MAX,param.getAge());

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
