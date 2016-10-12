package com.lishi.recruit.view.sys;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lishi.recruit.util.system.DataCopy;
import com.lishi.recruit.view.BaseCotroller;

@Controller
public class DataCopyController extends BaseCotroller  {
	
	private String datapath;
	
	private String datafile;
	
	private String delstate;
	
	/**
	 * 备份数据库
	 */
	@ResponseBody
	public String backup(){
		String result = "false";
		try {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
			String d = bartDateFormat.format(new Date());
			DataCopy.backup(datapath+"data_"+d+".sql");
			result = "true";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 恢复数据库
	 */
	@ResponseBody
	public String load() {
		String result = "false";
		try {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
			String d = bartDateFormat.format(new Date());
			DataCopy.backup("D:\\Data\\old_"+d+".sql");
			DataCopy.load(datafile);
			result ="true";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 系统初始化（删除所有营业数据）
	 */
	@ResponseBody
	public String delete(){
		String result = "false";
		try {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
			String d = bartDateFormat.format(new Date());
			DataCopy.backup("D:\\Data\\del_"+d+".sql");
			DataCopy.delete(delstate);
			result ="true";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setDatapath(String datapath) {
		this.datapath = datapath;
	}

	public void setDatafile(String datafile) {
		this.datafile = datafile;
	}

	public void setDelstate(String delstate) {
		this.delstate = delstate;
	}
	
	
}
