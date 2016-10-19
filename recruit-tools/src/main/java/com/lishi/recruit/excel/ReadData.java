package com.lishi.recruit.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lishi.recruit.tools.StringUtil;
 
public class ReadData {
	    
	  
	    
	 public static void readTxtFile(String filePath){
	        try {
	                String encoding="utf-8";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    List<String> phoneList = new ArrayList<String>();
	                    while((lineTxt = bufferedReader.readLine()) != null){
//	                        System.out.println(lineTxt);
	                        phoneList.add(lineTxt);
	                     List<String> data =  Arrays.asList(lineTxt.split(","));
	                     System.out.println(data.size());
//	                     StringBuffer sbf = new StringBuffer("insert into t_temp values(?,?,?,?,?)");
	                     Connection con = getConnection();
	                     PreparedStatement  pst = con.prepareStatement("insert into t_temp values(?,?,?,?,?)");
	                     for (int i = 0; i < data.size(); i++) {
	                    	
	                    	 
	                    	 if(i<4)
	                    	 {
	                    		 pst.setString(i+1, data.get(i));
	                    	 }else
	                    	 {
	                    		 pst.setFloat(5, Float.valueOf(data.get(i)));
	                    		 
	                    		
	  	  	         	       try {  
	  	  	         	    	   
	  	  	         	    	   
	  	  	         	    	 Boolean isFalse =  pst.execute();
	  	  	         	            
//	  	  	         	             System.out.println(isFalse);
//	  	  	         	            db1.close();//关闭连接  
	  	  	         	        } catch (SQLException e) {  
	  	  	         	            e.printStackTrace();  
	  	  	         	        } finally
	  	  	         	        {
	  	  	         	       pst.close();
	  	  	         	       con.close();
	  	  	         	        }
	                    	 }
	                    	
						}

	         	       
	                     
	         	      
	                     
	                     
	                     
	                       // System.out.println(MdSmsUtils.sendSmsMt(lineTxt, "亲，您参加开通海捣微店一元购红酒活动，但发现您未开通海捣微店，请尽快开通，否则订单于7天后关闭，根据协议1元订购金不予退还。【海捣网】", null, null, null));
	                    }
//	                    cleanRepeatData(phoneList);
//	                    for(String i:phoneList){  
//	          	    	  System.out.println(i);  
//	          	    	 }  
	                    read.close();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
	     
	    }
	 
	 /**
	  * 读文本列表
	  * @param filePath
	  * @return
	  */
	 public static List<String> readFileList(String filePath){
		 List<String> list = new ArrayList<String>();
		 try {
			 String encoding="utf-8";
			 File file=new File(filePath);
			 if(file.isFile() && file.exists()){ //判断文件是否存在
				 InputStreamReader read = new InputStreamReader(
						 new FileInputStream(file),encoding);//考虑到编码格式
				 BufferedReader bufferedReader = new BufferedReader(read);
				 String lineTxt = null;
				 while((lineTxt = bufferedReader.readLine()) != null){
					     list.add(lineTxt);
				 }
				 read.close();
			 }else{
				 System.out.println("找不到指定的文件");
			 }
		 } catch (Exception e) {
			 System.out.println("读取文件内容出错");
			 e.printStackTrace();
		 }
		 return list;
	 }
	 /**
	  * 读文本列表
	  * @param filePath
	  * @return
	  */
	 public static FileInputStream readFile(String filePath){
//		 List<String> list = new ArrayList<String>();
		 try {
//			 String encoding="utf-8";
			 File file=new File(filePath);
			 if(file.isFile() && file.exists()){ //判断文件是否存在
				 return new FileInputStream(file);
			 }else{
				 System.out.println("找不到指定的文件");
			 }
		 } catch (Exception e) {
			 System.out.println("读取文件内容出错");
			 e.printStackTrace();
		 }
		 return null;
	 }
	     
	    public static void main(String argv[]){
	        String filePath = "D:/temp/9.xlsx";
	        Example exmple=new Example(filePath);
	        List<String[]> list= exmple.getAllData(0);
//	        handleHdwOrder(list);
//	        handleZfb(list);
//	        handleYfj(list);
	        handleWx(list);
	      
	      
	    }
	    
	    /**
	     * 处理海捣网订单
	     * @param list
	     */
	    public static void handleHdwOrder(List<String[]> list)
	    {
	    	  Connection con = getConnection();
		        try {
//		        	List<Map<String,Object>> list=readExcel.readExcelHdwOrder(fis);
		        	  if(null!=list&&!list.isEmpty())
		        	  {
		        		  for (int i = 1; i < list.size(); i++) {
		        			  String[] str = list.get(i);
		        			  PreparedStatement  pst =null;
		        			  try {  
	 	  	         	    	   
		        				  pst = con.prepareStatement("insert into t_hdw_temp(orderId,reMoney) values(?,?)");
		        				  pst.setString(1, str[0]);
		        				  pst.setDouble(2, Double.valueOf(str[2]));
//		        				  pst.setDouble(3, Double.valueOf(str[3]));
	 	  	         	    	   
		  	  	         	    	 Boolean isFalse =  pst.execute();
		  	  	         	            
		  	  	         	        System.out.println("是否执行成功-->"+str[0]+"------>"+isFalse);
//		  	  	         	            db1.close();//关闭连接  
		  	  	         	        } catch (SQLException e) {  
		  	  	         	            e.printStackTrace();  
		  	  	         	        } finally
		  	  	         	        {
		  	  	         	        	if(null!=pst)
		  	  	         	        	{
		  	  	         	        		pst.close();
		  	  	         	        	}
		  	  	         	        }
						  }
		        		  
		        	  }
//				} catch (IOException e) {
//					e.printStackTrace();
			    } catch (SQLException e) {
			    	e.printStackTrace();
			    }finally
			    {
			    	if(null!=con)
						try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    }
	    }
	    
	    /**
	     * 处理支付宝收款
	     * @param list
	     */
	    public static void handleZfb(List<String[]> list)
	    {
	    	Connection con = getConnection();
	    	try {
//		        	List<Map<String,Object>> list=readExcel.readExcelHdwOrder(fis);
	    		if(null!=list&&!list.isEmpty())
	    		{
	    			for (int i = 1; i < list.size(); i++) {
	    				String[] str = list.get(i);
	    				PreparedStatement  pst =null;
	    				try {  
	    					System.out.println("--->"+str[6]);
	    					if(!str[7].equals("0")&&!str[7].equals(""))
	    					{
	    						pst = con.prepareStatement("insert into t_zfb_temp(orderId,reMoney) values(?,?)");
	    						pst.setString(1, str[6]);
	    						pst.setDouble(2, Double.valueOf(str[7]));
//	    						pst.setDouble(3, Double.valueOf(str[8]));
	    						Boolean isFalse =  pst.execute();
	    						System.out.println("是否执行成功-->"+str[0]+"------>"+isFalse);
	    					}
	    					
	    					
//		  	  	         	            db1.close();//关闭连接  
	    				} catch (SQLException e) {  
	    					e.printStackTrace();  
	    				} finally
	    				{
	    					if(null!=pst)
	    					{
	    						pst.close();
	    					}
	    				}
	    			}
	    			
	    		}
//				} catch (IOException e) {
//					e.printStackTrace();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}finally
	    	{
	    		if(null!=con)
	    			try {
	    				con.close();
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	}
	    }
	    
	    /**
	     * 处理易付金收款
	     * @param list
	     */
	    public static void handleYfj(List<String[]> list)
	    {
	    	Connection con = getConnection();
	    	try {
//		        	List<Map<String,Object>> list=readExcel.readExcelHdwOrder(fis);
	    		if(null!=list&&!list.isEmpty())
	    		{
	    			for (int i = 1; i < list.size(); i++) {
	    				String[] str = list.get(i);
	    				PreparedStatement  pst =null;
	    				try {  
	    					System.out.println("--->"+str[7]);
	    					if(!str[15].equals("0")&&!str[15].equals(""))
	    					{
	    						pst = con.prepareStatement("insert into t_yfj_temp(orderId,reMoney) values(?,?)");
	    						String orderNumLong = str[13];
	    						String orderNum =orderNumLong.split("_")[0];
	    						pst.setString(1, orderNum);
	    						pst.setDouble(2, Double.valueOf(str[15]));
	    						Boolean isFalse =  pst.execute();
	    						System.out.println("是否执行成功-->"+str[0]+"------>"+isFalse);
	    					}
	    					
	    					
//		  	  	         	            db1.close();//关闭连接  
	    				} catch (SQLException e) {  
	    					e.printStackTrace();  
	    				} finally
	    				{
	    					if(null!=pst)
	    					{
	    						pst.close();
	    					}
	    				}
	    			}
	    			
	    		}
//				} catch (IOException e) {
//					e.printStackTrace();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}finally
	    	{
	    		if(null!=con)
	    			try {
	    				con.close();
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	}
	    }
	    
	    
	    /**
	     * 处理微信收款
	     * @param list
	     */
	    public static void handleWx(List<String[]> list)
	    {
	    	Connection con = getConnection();
	    	try {
//		        	List<Map<String,Object>> list=readExcel.readExcelHdwOrder(fis);
	    		if(null!=list&&!list.isEmpty())
	    		{
	    			for (int i = 1; i < list.size(); i++) {
	    				String[] str = list.get(i);
	    				PreparedStatement  pst =null;
	    				try {  
	    					System.out.println("--->"+str[18]);
	    					if(!str[18].equals("0")&&!str[18].equals(""))
	    					{
	    						pst = con.prepareStatement("insert into t_wx_temp(orderId,reMoney) values(?,?)");
	    						String orderNumLong = str[18];
	    						String orderNum =orderNumLong.split("`")[1];
	    						pst.setString(1, orderNum);
	    						pst.setDouble(2, Double.valueOf(str[19]));
	    						Boolean isFalse =  pst.execute();
	    						System.out.println("是否执行成功-->"+str[18]+"------>"+isFalse);
	    					}
	    					
	    					
//		  	  	         	            db1.close();//关闭连接  
	    				} catch (SQLException e) {  
	    					e.printStackTrace();  
	    				} finally
	    				{
	    					if(null!=pst)
	    					{
	    						pst.close();
	    					}
	    				}
	    			}
	    			
	    		}
//				} catch (IOException e) {
//					e.printStackTrace();
	    	} catch (SQLException e) {
	    		e.printStackTrace();
	    	}finally
	    	{
	    		if(null!=con)
	    			try {
	    				con.close();
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    	}
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
	    
	    
	    
	    public static  Connection getConnection() { 
	    	Connection  conn =null;
	        try {  
	        	String url = "jdbc:mysql://172.16.2.59/test";  
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
	  
	    
}
