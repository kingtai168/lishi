package com.lishi.recruit.excel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lishi.recruit.excel.model.MergeReport;
import com.lishi.recruit.excel.model.Order;
import com.lishi.recruit.excel.writer.service.ExcelWriter;
import com.lishi.recruit.excel.writer.service.impl.ExcelPoiWriterImpl;
import com.lishi.recruit.excel.writer.utils.Bean2MAP;
import com.lishi.recruit.tools.DateTimeUtil;
import com.lishi.recruit.tools.StringUtil;
 
public class ReadData {
	    
	public static void contentToTxt(String filePath,List<String> list) {                                    
//	       String str = new String(); //原有txt内容                                                       
	       String s1 = new String();//内容更新                                                            
	       try {                                                                                          
	           File f = new File(filePath);                                                               
	           if (f.exists()) {                                                                          
	               System.out.print("文件存在");                                                          
	           } else {                                                                                   
	               System.out.print("文件不存在");                                                        
	               f.createNewFile();// 不存在则创建                                                      
	           }                                                                                          
	           BufferedReader input = new BufferedReader(new FileReader(f));                              
	                                                                                                      
//	            while ((str = input.readLine()) != null) {                                                
//	                s1 += str + "\n";                                                                     
//	            }                           
	           if(null!=list&&!list.isEmpty())
	           {
	        	   for (String str : list) {
	        		   s1 += str + "\n";    
				    }
	           }
//	            System.out.println(s1);                                                                   
	            input.close();                                                                            
//	            s1 += content;                                                                            
	                                                                                                      
	            BufferedWriter output = new BufferedWriter(new FileWriter(f));                            
	            output.write(s1);                                                                         
	            output.close();                                                                           
	        } catch (Exception e) {                                                                       
	            e.printStackTrace();                                                                      
	                                                                                                      
	        }                                                                                             
	    }                                                                                                 

	    
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
	    						pst = con.prepareStatement("insert into t_yfj_temp(orderId,yfjOrderId,reMoney) values(?,?,?)");
	    						String orderNumLong = str[13];
	    						String orderNum =orderNumLong.split("_")[0];
	    						pst.setString(1, orderNum);
	    						pst.setString(2, orderNumLong);
	    						pst.setDouble(3, Double.valueOf(str[15]));
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
	    
	    /**
	     * 保存错误数据
	     * 向excel插入数据，生成临时文件
	     * @throws Exception 
	     */
	    private static String saveErrorToExcel(List<MergeReport> mergeReportlist) throws Exception {
	    	String temp_fileName = "";
	    	if(StringUtil.isNotEmpty(mergeReportlist)){
				List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
				for(MergeReport eor:mergeReportlist){
					Map<String, Object> map = new HashMap<String, Object>();
					Bean2MAP.addObject2Map(eor, map);
					result.add(map);
				}
				HashMap root = new HashMap();
				root.put("list", result);
				
				/**
				 * 根据模板生成临时文件并导出文件
				 */
				ExcelWriter writer = new ExcelPoiWriterImpl();
				
				//模板路径
//				String rootPath = this.getRequest().getSession().getServletContext().getRealPath("/");
//				String template_path = rootPath + "excelTemplate/";
				//模板名称
				String template_fileName ="D:/temp/orderTemplate.xls";
				
				//临时文件命名
				temp_fileName = "D:/temp/order_"+DateTimeUtil.getymdhmsCurrentTimeString()+".xls";
				//生成临时报表文件
				writer.genarateFile(template_fileName,temp_fileName, root);
			}
	    	return temp_fileName;
		}
	    /**
	     * 保存错误数据
	     * 向excel插入数据，生成临时文件
	     * @throws Exception 
	     */
	    private static String saveErrorToExcel(List<MergeReport> mergeReportlist,String srcFileName,String targetFileName) throws Exception {
	    	String temp_fileName = "";
	    	if(StringUtil.isNotEmpty(mergeReportlist)){
	    		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
	    		for(MergeReport eor:mergeReportlist){
	    			Map<String, Object> map = new HashMap<String, Object>();
	    			Bean2MAP.addObject2Map(eor, map);
	    			result.add(map);
	    		}
	    		HashMap root = new HashMap();
	    		root.put("list", result);
	    		
	    		/**
	    		 * 根据模板生成临时文件并导出文件
	    		 */
	    		ExcelWriter writer = new ExcelPoiWriterImpl();
	    		
	    		//模板路径
//				String rootPath = this.getRequest().getSession().getServletContext().getRealPath("/");
//				String template_path = rootPath + "excelTemplate/";
	    		//模板名称
//	    		String template_fileName ="D:/temp/orderHwdMainTemplate.xls";
//	    		
//	    		//临时文件命名
//	    		temp_fileName = "D:/temp/hwdMainOrder_"+DateTimeUtil.getymdhmsCurrentTimeString()+".xls";
	    		//生成临时报表文件
	    		writer.genarateFile(srcFileName,targetFileName, root);
	    	}
	    	return temp_fileName;
	    }
	    
	    
	    public static void mainZfOrder()
	    {
	    	List<Order> orderList= new ArrayList<Order>();
	    	List<MergeReport> mergeReportList = new ArrayList<MergeReport>();
	    	Order order = null;
	    	try {
		    	  Connection con = getConnection();
		    	  PreparedStatement  pst =null;
		    	  pst = con.prepareStatement("SELECT * FROM t_zf_total_temp");
		    	  ResultSet result =  pst.executeQuery();
		    	  while(result.next()) {
	    		     order = new Order();
		    		 String orderId= result.getString("orderId");
		    		 String yfjOrderId= result.getString("yfjOrderId");
		    		 Double reMoney = result.getDouble("reMoney");
		    		 String flag= result.getString("flag");
		    		 order.setOrderId(orderId);
		    		 order.setFlag(flag);
		    		 order.setYfjOrderId(yfjOrderId);
		    		 order.setReMoney(reMoney);
		    		 orderList.add(order);
		    		  
		    	  }
		    	  if(null!=orderList&&!orderList.isEmpty())
		    	  {
			    		  for (Order temp : orderList) {
			    			  MergeReport mergeReport = new MergeReport();
			    			  String isOrder ="N";
			    			  List<Order> hdwList= new ArrayList<Order>();
			    			  //根据hdw订单查询支付
			    			  pst = con.prepareStatement("SELECT * FROM t_hdw_temp where orderId = ? and reMoney=?");
			    			  pst.setString(1, temp.getOrderId());
			    			  pst.setDouble(2, temp.getReMoney());
			    			  ResultSet result1 =  pst.executeQuery();
			    			  while(result1.next()) {
		    				      order = new Order();
		    		    		 String orderId= result1.getString("orderId");
		    		    		 Double reMoney = result1.getDouble("reMoney");
		    		    		 order.setOrderId(orderId);
		    		    		 order.setReMoney(reMoney);
		    		    		
		    		    		 
		    		    		 hdwList.add(order);
		    			  }
		    			  mergeReport.setZfbOrderId(temp.getOrderId());
		    			  mergeReport.setZfbReMoney(temp.getReMoney().toString());
		    			  if(null!=hdwList&&!hdwList.isEmpty())
		    			  {
		    				  Order ord=hdwList.get(0);
		    				  String orderId= ord.getOrderId();
		    		    	  Double reMoney = ord.getReMoney();
		    		    	  mergeReport.setHdwOrderId(orderId);
		    		    	  mergeReport.setHdwReMoney(reMoney.toString());
		    		    	 
		    		    	  isOrder="Y";
		    			  }else
		    			  {
		    				  mergeReport.setMessage("海捣网明细报表没查询到数据！");
		    			  }
		    			  //设置 wxReMoney--支付方式
		    			  mergeReport.setWxReMoney(temp.getFlag());
		    			  mergeReport.setIsZf(isOrder);
		    			  mergeReportList.add(mergeReport);
		    		  }
		    	  }
		    	  
		    	  
		    	  String template_fileName ="D:/temp/zfHwdMainTemplate.xls";
		    	 //target file
		    	 String temp_fileName = "D:/temp/支付订单_"+DateTimeUtil.getymdhmsCurrentTimeString()+".xls";
				saveErrorToExcel(mergeReportList,template_fileName,temp_fileName);
	    	}catch(Exception e)
	    	{
	    		
	    	}
	    }
	    
	    public static void main(String[] args) {
	    	mainHdwOrder();
	    	mainZfOrder();
		}
	    public static void mainHdwOrder(){
	    	
	    	List<Order> orderList= new ArrayList<Order>();
	    	List<MergeReport> mergeReportList = new ArrayList<MergeReport>();
//	    	List<String>  list= new ArrayList<String>();
	    	
	    	Order order = null;
	      try {
	    	  Connection con = getConnection();
	    	  PreparedStatement  pst =null;
	    	  pst = con.prepareStatement("SELECT * FROM t_hdw_temp");
	    	  ResultSet result =  pst.executeQuery();
	    	  while(result.next()) {
	    		  order = new Order();
	    		 String orderId= result.getString("orderId");
	    		 Double reMoney = result.getDouble("reMoney");
	    		 order.setOrderId(orderId);
	    		 order.setReMoney(reMoney);
	    		 orderList.add(order);
//	    		 list.add(orderId);
	    		} 
//	    	  System.out.println("SIZE-前->"+list.size());
//	    	 cleanRepeatData(list);
//	    	 System.out.println("SIZE-后->"+list.size());
	    	  //
	    	  if(null!=orderList&&!orderList.isEmpty())
	    	  {
	    		  for (Order temp : orderList) {
	    			  MergeReport mergeReport = new MergeReport();
	    			  String isZf ="N";//是否有支付
	    			  List<Order> zfList= new ArrayList<Order>();
	    			  //根据hdw订单查询支付
	    			  pst = con.prepareStatement("SELECT * FROM t_zf_total_temp where orderId = ? and reMoney=?");
	    			  pst.setString(1, temp.getOrderId());
	    			  pst.setDouble(2, temp.getReMoney());
	    			  ResultSet result1 =  pst.executeQuery();
	    			  System.out.println("---------->");
	    			  while(result1.next()) {
	    				      order = new Order();
	    		    		 String orderId= result1.getString("orderId");
	    		    		 Double reMoney = result1.getDouble("reMoney");
	    		    		 String flag = result1.getString("flag");
	    		    		 String yfjOrderId = result1.getString("yfjOrderId");
	    		    		 order.setOrderId(orderId);
	    		    		 order.setReMoney(reMoney);
	    		    		 order.setFlag(flag);
	    		    		 order.setYfjOrderId(yfjOrderId);
	    		    		 zfList.add(order);
	    			  }
	    			  
	    			  mergeReport.setHdwOrderId(temp.getOrderId());
	    			  mergeReport.setHdwReMoney(temp.getReMoney().toString());
	    			  if(null!=zfList&&!zfList.isEmpty())
	    			  {
	    				  
	    				  Order ord=zfList.get(0);
						  String f= ord.getFlag();
						   if(f.equals("微信支付"))
						   {
							   mergeReport.setWxOrderId(ord.getOrderId());
							   mergeReport.setWxReMoney(ord.getReMoney().toString());
						   }
						   else if(f.equals("支付宝支付"))
						   {
							   mergeReport.setZfbOrderId(ord.getOrderId());
							   mergeReport.setZfbReMoney(ord.getReMoney().toString());
						   }
						   else
						   {
							   //易汇金支付
							   
							   mergeReport.setYfjOrderId(ord.getYfjOrderId());
							   mergeReport.setYfjReMoney(ord.getReMoney().toString());
						   }
						   isZf = "Y";
	    			  }else
	    			  {
	    				  mergeReport.setMessage("没查询到支付数据!");
	    			  }
	    			 
	    			  mergeReport.setIsZf(isZf);
	    			  mergeReportList.add(mergeReport);
				}
	    		  
	    		  
	    		  
	    	  }
	    	  //src temp
	    	 String template_fileName ="D:/temp/orderHwdMainTemplate.xls";
	    	 //target file
	    	 String temp_fileName = "D:/temp/海捣订单_"+DateTimeUtil.getymdhmsCurrentTimeString()+".xls";
			saveErrorToExcel(mergeReportList,template_fileName,temp_fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
