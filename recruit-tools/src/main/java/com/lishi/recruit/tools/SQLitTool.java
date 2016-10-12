package com.lishi.recruit.tools;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.handlers.MapListHandler;

public class SQLitTool {

	/**
	 * 
	 * @Description  创建sqlite db 文件
	 * @Author  zt
	 * @CreateDate  Mar 20, 2014
	 */
	public static void createPolicydbFile(){
		
		String fullPath = "D:\\aa\\dbFile\\user.db";
		String[] arys = fullPath.split("\\\\");
		String filePath = fullPath.replaceAll(arys[arys.length-1], "");
		
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		File file1 = new File(fullPath);
		Connection conn  = null;
		Statement statement= null;
		if (!file1.exists()) {
			try {
				Class.forName("org.sqlite.JDBC");
//				log.info("++++++++++初始化政策的DB文件++++++++++++");
				conn = DriverManager.getConnection("jdbc:sqlite:" + fullPath);
				statement = conn.createStatement();
//				statement.executeUpdate("create table person(id INTEGER PRIMARY KEY AUTOINCREMENT," +
//						"outerId,airline,depAirport,arrAirport,tripType,saleStartDate,saleEndDate,	supplierOfficeId,	supplierId,	needSwitchPNR,	workTime,"+	
//						"refundTime,	excludeDepAirports,	excludeArrAirports,	invoiceType,contentEI,	earliestIssueDays,	lastestIssueDays,	minStayTime,	maxStayTime,"+
//						"isSupportCodeShare,	isRefund,	isChange,	isIssue,	isUpgrade,	refundType,	refundBaseType,	refundPriceInfo,timestamp,memo,segmentNum,	" +
//						"includeFlightNos,excludeFlightNos,operationTime,	travelStartDate,	travelEndDate,	depTimeRanges,	cabinList,price,	retentionPoint,retentionMoney,supportAgents,type,isDelete);");
				statement.executeUpdate("create table user(id integer primary key autoincrement,name, age,sex,createTime);");
				
			} catch (SQLException e) {
//				log.info("---------初始化政策的DB文件失败---------");
				e.printStackTrace();
			}
			catch(ClassNotFoundException ex)
			{
//				log.error("---------获取SQLITE,JDBC失败！--------------");
			}
			finally
			{
				if(null!=statement)
				{
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(null!=conn)
				{
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void add(String url,String sql){
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			Class.forName("org.sqlite.JDBC");
			url ="jdbc:sqlite:D:\\aa\\dbFile\\user.db";
			sql  = "insert into user(id,name,age,sex,createTime) values (?,?,?,?,?)";
			conn = DriverManager.getConnection(url);
			prep = conn.prepareStatement(sql);
			prep.setInt(1, 1);
			prep.setString(2, "张太");
			prep.setString(3, "18");
			prep.setString(4, "男");
			prep.setString(5, "2014-05-18 14:00:00");
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException ex)
		{
//			log.error("---------获取SQLITE,JDBC失败！--------------");
		}
		finally
		{
			if(null != prep)
			{
				try {
					prep.execute();
				} catch (SQLException e) {
				}
			}
			if(null != conn)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void updatePolicyLastTime(String url,String sql)
	{
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			Class.forName("org.sqlite.JDBC");
			url ="jdbc:sqlite:D:\\aa\\dbFile\\user.db";
			conn = DriverManager.getConnection(url);
			prep = conn.prepareStatement(sql);
			prep.setString(1, "");
			prep.setString(2, "");
			prep.setString(3, "");
			prep.execute();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			if(null != prep)
			{
				try {
					prep.execute();
				} catch (SQLException e) {
				}
			}
			if(null != conn)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
//public static List<Map<String,Object>> query(String sql) throws Exception {
//		
//		
//		List<Map<String,Object>> res = null;
//		QueryRunner qr=new QueryRunner();
//		Connection conn = null;
//		PreparedStatement prep = null;
//		Class.forName("org.sqlite.JDBC");
//		String url ="jdbc:sqlite:D:\\aa\\dbFile\\user.db";
//		sql = "select * from user";
//		conn = DriverManager.getConnection(url);
//		try {
//			res=(List<Map<String,Object>>) qr.query(conn, sql, new MapListHandler());
//			return res;
//		}catch (Exception e) {
//			throw e;
//		}
//	}
	public static void main(String[] args) {
		
		//createPolicydbFile();
		try {
//			List<Map<String,Object>> list =  query("");
//			System.out.println(list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
