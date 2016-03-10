package com.push.pushApi.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.push.bean.gen.NanoCheckerReport;
import com.push.dao.readdao.NanoCheckerRefReadMapper;
import com.push.pushApi.action.ApiBaseAction;

import net.sf.json.JSONArray;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class reportAction extends ApiBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1952672370753974717L;

	@Autowired
	NanoCheckerRefReadMapper nanoCheckerRefReadMapper;

	// 前台对应的参数bean
	private ReportInputBean param = new ReportInputBean();

	private JSONArray result;

	public String doExec() throws Exception {
		// 处理跨域请求问题
		super.sethttp(ServletActionContext.getResponse());

		// 将前台传来的参数copy到javabean中，方便处理
		// 病人名
		// 测量时间
		HttpServletRequest request = ServletActionContext.getRequest();
		BeanUtils.populate(param, request.getParameterMap());

		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = "2016-03-08 13:49:25";
		Date d = sim.parse(str);

		// 向前台返回值
		List<NanoCheckerReport> nanoCheckerReportList = null;

		 nanoCheckerReportList=nanoCheckerRefReadMapper.selectByPatientId(param.getPname(),sim.parse(param.getPtime()));
		//nanoCheckerReportList = nanoCheckerRefReadMapper.selectByPatientId("张三", d);

		// 将出力Bean转成json对象

		//result = JSONObject.fromObject(nanoCheckerReportList);// 将list转换为json数组

		// 将java对象转成json对象
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		result = JSONArray.fromObject(nanoCheckerReportList, jsonConfig);

	

		return SUCCESS;
	}

	public JSONArray getResult() {
		return result;
	}

	public void setResult(JSONArray result) {
		this.result = result;
	}
}
