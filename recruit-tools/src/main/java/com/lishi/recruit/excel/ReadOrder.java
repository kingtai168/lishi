package com.lishi.recruit.excel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lishi.recruit.excel.reader.ExcelReader;

public class ReadOrder {
	
	  public static  Connection getConnection() { 
	    	Connection  conn =null;
	        try {  
	        	String url = "jdbc:mysql://172.16.2.59/mb";  
	        	String name = "com.mysql.jdbc.Driver";  
	     	    String user = "hdw";  
	     	    String password = "hdw"; 
	            Class.forName(name);//指定连接类型  
	           conn = DriverManager.getConnection(url, user, password);//获取连接  
//	            pst = conn.prepareStatement(sql);//准备执行语句  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  return conn;
	    }  
	
	  public static void cleanRepeatData(List<String> list)
	    {
	    	
	    	 List<String> listTemp= new ArrayList<String>();  
	    	 Iterator<String> it=list.iterator();  
	    	 while(it.hasNext()){  
	    		 String a=it.next();  
	    	  if(listTemp.contains(a)){ 
	    	   it.remove();  
	    	  }  
	    	  else{  
	    	   listTemp.add(a);  
	    	  }  
	    	 }  
	    	
	    }
	 
	 public static void main(String[] args) {
		 ExcelReader reader = new ExcelReader("D:/temp/1.xlsx");
		 List<String[]> list= reader.getAllData(0);
		 List<String> orderIdList=new ArrayList<String>();
		 List<String> sqlList=new ArrayList<String>();
		 Connection con = getConnection();
		 if(null!=list&&!list.isEmpty())
		 {
			for (int i = 1; i < list.size(); i++) {
				String[] str = list.get(i);
				orderIdList.add(str[0]);
			}
			System.out.println(orderIdList.size());
			cleanRepeatData(orderIdList);
			System.out.println(orderIdList.size());
			
			for (String orderId : orderIdList) {
				PreparedStatement  pst =null;
				  try {  
					  pst = con.prepareStatement("SELECT d.SMO_ID as smoId,s.SPS_ID as standId,s.SPS_COST_PRICE as costPrice FROM shop_product_spec s "
					  		+ "LEFT JOIN shop_mall_order_detail d ON s.SP_ID=d.SP_ID WHERE d.SMO_ID=?");
					  pst.setString(1, orderId);
					 ResultSet result =  pst.executeQuery();
					 while(result.next()) {
						String smoId =result.getString("smoId");
						String standId =result.getString("standId");
						Double costPrice =result.getDouble("costPrice");
						String sql = "update shop_mall_order_detail d set d.SPS_COST_PRICE="+costPrice+"   WHERE d.SMO_ID ='"+smoId+"' and d.SMOD_STANDARD = '"+standId+"';";
						System.out.println(sql);
						sqlList.add(sql);
				      }
				  }
				  catch(Exception ex)
				  {
					  
				  }
			}
			System.out.println(sqlList.size());
			ReadData.contentToTxt("D:/temp/test.txt", sqlList);
		 }
	}
	 
	 
	 
}
