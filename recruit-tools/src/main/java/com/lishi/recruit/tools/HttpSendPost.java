package com.lishi.recruit.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;


@SuppressWarnings("unchecked")
public class HttpSendPost {

	// 连接超时，（单位：毫秒）
	private static int CONNECTTIMEOUT = 1000;
	// 请求编码
	private static String ENCODEING = "utf-8";

//	private static final Logger logger = Logger.getLogger(HttpSendPost.class);

	/**
	 * 描述：发送带参数的POST的HTTP请求
	 * 
	 * @param reqUrl
	 *            HTTP请求URL
	 * @param parameters
	 *            参数映射表
	 * @return HTTP响应的字符串
	 * @throws Exception
	 */
	public static String doPost(String reqUrl, Map parameters) {
		return doPost(reqUrl, parameters, ENCODEING);
	}

	private static String doPost(String reqUrl, Map parameters, String encoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			StringBuilder params = new StringBuilder();
			for (Iterator iter = parameters.entrySet().iterator(); iter
					.hasNext();) {
				Entry element = (Entry) iter.next();
				params.append(element.getKey().toString());
				params.append("=");
				params.append(URLEncoder.encode(element.getValue().toString(),
						encoding));
				params.append("&");
			}

			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}

			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("POST");
			url_con.setConnectTimeout(CONNECTTIMEOUT);
			url_con.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();

			BufferedReader rd = new BufferedReader(new InputStreamReader(in,
					ENCODEING));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			rd.close();
			in.close();
		} catch (IOException e) {
			System.err.println("网络故障");
//			logger.info("--------------------->网络故障");
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return responseContent;
	}
	
	/**
	 * 描述：文件以二进制流POST的HTTP请求
	 * 
	 * @param reqUrl
	 *            HTTP请求URL
	 * @param fileUrl
	 *            文件地址
	 * @return HTTP响应的字符串
	 * @throws Exception
	 */
//	public static String doPostFile(String reqUrl, String fileUrl) {
//		return doPostFile(reqUrl, fileUrl, ENCODEING);
//	}

//	private static String doPostFile(String reqUrl, String fileUrl, String encoding) {
//		HttpURLConnection url_con = null;
//		String responseContent = null;
//		try {
//
//			URL url = new URL(reqUrl);
//
//			url_con = (HttpURLConnection) url.openConnection();
//			url_con.setRequestMethod("POST");
//			url_con.setConnectTimeout(CONNECTTIMEOUT);
//			url_con.setDoOutput(true);
//			url_con.setRequestProperty("Content-type","application/x-java-serialized-object");
//			
//			File file = new File(fileUrl);
//		    InputStream ins = new FileInputStream(file);
//		    byte[] data = IOUtils.toByteArray(ins);
//		   
//			url_con.getOutputStream().write(data, 0, data.length);
//			url_con.getOutputStream().flush();
//			url_con.getOutputStream().close();
//
//			InputStream in = url_con.getInputStream();
//			BufferedReader rd = new BufferedReader(new InputStreamReader(in,
//					ENCODEING));
//			String tempLine = rd.readLine();
//			StringBuffer tempStr = new StringBuffer();
//			String crlf = System.getProperty("line.separator");
//			while (tempLine != null) {
//				tempStr.append(tempLine);
//				tempStr.append(crlf);
//				tempLine = rd.readLine();
//			}
//			responseContent = tempStr.toString();
//			rd.close();
//			in.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.err.println("网络故障");
//			logger.info("--------------------->网络故障"+e.getMessage());
//		} finally {
//			if (url_con != null) {
//				url_con.disconnect();
//			}
//		}
//		return responseContent;
//	}
	
	/**
	 * 描述：json的HTTP请求
	 * 
	 * @param reqUrl
	 *            HTTP请求URL
	 * @param json
	 *            字符串
	 * @return HTTP响应的字符串
	 * @throws Exception
	 */
//	public static String doPostJSON(String reqUrl, String json) {
//		return doPostJSON(reqUrl, json, ENCODEING);
//	}
	
//	public static String doPostJSON(String reqUrl, String json, String encoding) {
//		HttpURLConnection url_con = null;
//		String responseContent = null;
//		OutputStream outputStream = null;
//		 InputStream inputStream = null;
//		 BufferedReader bufferedReader = null;
//		 InputStreamReader inputStreamReader = null;
//		 
//		try {
//
//			URL url = new URL(reqUrl);
//
//			url_con = (HttpURLConnection) url.openConnection();
//			url_con.setRequestMethod("POST");
////			url_con.setConnectTimeout(CONNECTTIMEOUT);
//			url_con.setDoOutput(true);
//			url_con.setDoInput(true);
//			url_con.setUseCaches(false);  
//			url_con.setRequestProperty("Content-type","application/x-java-serialized-object");
//			url_con.connect(); 
//			if(StringUtils.isNotEmpty(json))
//			{
//				 outputStream = url_con.getOutputStream(); 
//				 outputStream.write(json.getBytes("UTF-8"));
//			}
//			 inputStream = url_con.getInputStream(); 
//			 inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
//	         bufferedReader = new BufferedReader(inputStreamReader);  
//	         String str = null;  
//	         StringBuffer buffer = new StringBuffer();  
//	         while ((str = bufferedReader.readLine()) != null) {  
//
//	                         buffer.append(str);  
//
//	                     }  
//
//                 responseContent = buffer.toString();
//		}catch(Exception ex)
//		{
//			ex.printStackTrace();
//		}finally
//		{
//			// 释放资源
//			try {
//				if (null != outputStream) {
//
//					outputStream.close();
//				}
//				if (null != bufferedReader) {
//					
//					bufferedReader.close();
//				}
//				if (null != inputStreamReader) {
//					
//					inputStreamReader.close();
//				}
//				if (null != inputStream) {
//					
//					inputStream.close(); 
//				}
//				if (null != url_con) {
//					
//					 url_con.disconnect();  
//				}
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		return responseContent;
//	}
	public static void main(String[] args) {
		Map parameters = new HashMap();
		parameters.put("token", "vowsong");
		parameters.put("access", "at");
		StringBuilder params = new StringBuilder();
		for (Iterator iter = parameters.entrySet().iterator(); iter
				.hasNext();) {
			Entry element = (Entry) iter.next();
			params.append(element.getKey().toString());
			params.append("=");
			try {
				params.append(URLEncoder.encode(element.getValue().toString(),
						ENCODEING));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			params.append("&");
		}

		if (params.length() > 0) {
			params = params.deleteCharAt(params.length() - 1);
		}
		
		System.out.println("?"+params.toString());
	}
}
