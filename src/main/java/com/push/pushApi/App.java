package com.push.pushApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.push.bean.gen.NanoCheckerReport;
import com.push.dao.readdao.NanoCheckerRefReadMapper;

/**
 * Hello world!
 *
 */
public class App 
{
	
	
	
    public static void main( String[] args ) throws ParseException
    {
    	
    	List<NanoCheckerReport> nanoCheckerReportList =  null;
		
    	SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	String str="2016-03-08 13:49:25.000";
    	Date d=sim.parse(str);
    	
		//nanoCheckerReportList=nanoCheckerRefReadMapper.selectByPatientId("张三", d);
        System.out.println( "Hello World!" );
    }
}
