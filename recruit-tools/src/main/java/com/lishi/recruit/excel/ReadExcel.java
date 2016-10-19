package com.lishi.recruit.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.lishi.recruit.tools.StringUtil;

/**
 * 
 * @author Administrator
 *
 */
public class ReadExcel {
	
	
	public List<Map<String,Object>> readExcelHdwOrder(FileInputStream inputStream)throws IOException
	{
//		InputStream fis = inputStream;
//		 InputStream inp = new FileInputStream(path);  
//		Workbook wb = null;
//	      wb = WorkbookFactory.create(inputStream);     
		
		
		List<Map<String,Object>> resultList= new ArrayList<Map<String,Object>>();
   	 //创建Excel工作薄  
       HSSFWorkbook hwb = new HSSFWorkbook(inputStream);  
       //得到第一个工作表  
       HSSFSheet sheet = hwb.getSheetAt(0);  
       HSSFRow row = null;
       Map<String,Object> map=null;
       //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数   
       for(int i = 0; i < hwb.getNumberOfSheets(); i++) {  
    	   sheet = hwb.getSheetAt(i);  
           //遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数  
           for(int j = 2; j < sheet.getPhysicalNumberOfRows(); j++) {  
               row = sheet.getRow(j);
               String orderId = getCellValue(row.getCell(0),null);
               map=new HashMap<String,Object>();
               if(StringUtil.isNotEmpty(orderId))
               {
            	   map.put("orderId", orderId);
               }
               Double reMoney = Double.valueOf(getCellValue(row.getCell(2),null));
               Double orderMoney = Double.valueOf(getCellValue(row.getCell(3),null));
               map.put("reMoney", reMoney);
               map.put("orderMoney", orderMoney);
               resultList.add(map);
           }
       }
       return resultList;
	}

	
	 /**
	   * 读取 行-单元格cell，判断格式
	   * @param cell
	   */
	    private  String getCellValue(HSSFCell cell,String cellFrom){
	    	DecimalFormat df = new DecimalFormat("#");
	        String value = null;  
	        //查检列类型  
	        if(StringUtil.isEmpty(cell)){
	        	return null;
	        }
	        switch(cell.getCellType()){  
	            case HSSFCell.CELL_TYPE_STRING://字符串  
	                value = cell.getRichStringCellValue().getString().trim();  
	                break;  
	            case HSSFCell.CELL_TYPE_NUMERIC://数字  
	            	if(HSSFDateUtil.isCellDateFormatted(cell)){
	    				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    				value= sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
	    			}else{
	    				if(StringUtil.isNotEmpty(cellFrom)){
		    				if("spsStock".equals(cellFrom) || "spSaleStock".equals(cellFrom) || "spBaseNum".equals(cellFrom)){
		    					value= df.format(cell.getNumericCellValue());
		    				}else{
		    					value = String.valueOf(cell.getNumericCellValue());
		    				}
	    				}else{
	    					value = String.valueOf(cell.getNumericCellValue());
	    				}
	    				
	    			}
	                break;  
	            case HSSFCell.CELL_TYPE_BLANK:  
	                value = "";  
	                break;     
	            case HSSFCell.CELL_TYPE_FORMULA:  
	                value = String.valueOf(cell.getCellFormula());  
	                break;  
	            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值  
	                value = String.valueOf(cell.getBooleanCellValue());  
	                break;  
	            case HSSFCell.CELL_TYPE_ERROR:  
	                value = String.valueOf(cell.getErrorCellValue());  
	                break;  
	            default:  
	                break;  
	        }  
	        return value; 
	    }
}
