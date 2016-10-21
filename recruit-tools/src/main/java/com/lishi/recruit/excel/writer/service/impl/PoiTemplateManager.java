package com.lishi.recruit.excel.writer.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * 使用POI实现操作EXCEL文件管理类
 * 
 * @author wmchen
 * @version 1.0
 * @since  2015-6-26
 *
 */
public class PoiTemplateManager implements ExcelTemplateManager
{
	
	private Workbook wb = null;
	private Sheet sheet = null;
	private int lastRowNum;
	private Map<Integer, Integer> lastRowNums;
	
	@Override
	public void open(File templeateFile) throws Exception
	{
		InputStream in = null;
		try
		{
			in = new FileInputStream(templeateFile);
			wb = WorkbookFactory.create(in);
			sheet = wb.getSheetAt(0);
			lastRowNum = sheet.getLastRowNum();
			lastRowNums = new HashMap<Integer, Integer>();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (in != null)
				in.close();
		}
	}
	
	@Override
	public void makeTag(Map<String, Object> data) throws Exception
	{
		
		Iterator<Map.Entry<String, Object>> iter = data.entrySet().iterator();
		
		while (iter.hasNext())
		{
			
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iter.next();
			String key = entry.getKey();
			Object val = entry.getValue();
			
			if (val != null && val instanceof Collection)
			{
				makeTag(key, (Collection<?>) val);
				continue;
			}
			else
			{
				makeTag(key, val);
			}
		}
	}
	
	@Override
	public void makeTag(String tagName, Object value)
	{
		Cell cell = findCell(sheet, tagName);
		if (cell != null)
		{
			setValue(cell, value);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void makeTag(String tagName, Collection<?> values) throws Exception
	{
		Cell cell = findCell(sheet, tagName);
		if (null == cell)
		{
			return;
		}
		Row tagRow = cell.getRow();// 标记所在行
		Row fieldRow = sheet.getRow(tagRow.getRowNum() + 1);// 列表字段所在行
		int rowNum = fieldRow.getRowNum() + 1;
		if (values != null && values.size() > 0)
		{
			
			sheet.shiftRows(rowNum, Math.max(rowNum, lastRowNum), values.size());// 移动剩余的行
			
			lastRowNum = lastRowNum + values.size();
			for (Object bean : values)
			{
				
				Row row = sheet.createRow(rowNum);
				
				// 将fieldRow的样式完全复制给row
				
				Iterator<Cell> rawCells = fieldRow.cellIterator();
				
				if (bean instanceof Map)
				{
					
					Map<String, Object> rowData = (Map<String, Object>) bean;
					
					while (rawCells.hasNext())
					{
						Cell rawCell = rawCells.next();
						String fieldName = rawCell.getStringCellValue();
						if (fieldName != null && !"".equals(fieldName.trim()))
						{
							Cell valueCell = row.createCell(rawCell.getColumnIndex());
							valueCell.setCellStyle(rawCell.getCellStyle());
							
							Object value = rowData.get(fieldName);
							setValue(valueCell, value);
						}
						else
						{
							Cell valueCell = row.createCell(rawCell.getColumnIndex());
							valueCell.setCellStyle(rawCell.getCellStyle());
							setValue(valueCell, "");
						}
					}
				}
				else
				{
					while (rawCells.hasNext())
					{
						Cell rawCell = rawCells.next();
						String fieldName = rawCell.getStringCellValue();
						if (fieldName != null && !"".equals(fieldName.trim()))
						{
							Cell valueCell = row.createCell(rawCell.getColumnIndex());
							valueCell.setCellStyle(rawCell.getCellStyle());
							Field field = bean.getClass().getDeclaredField(fieldName);
							field.setAccessible(true);
							Object value = field.get(bean);
							setValue(valueCell, value);
						}
						else
						{
							Cell valueCell = row.createCell(rawCell.getColumnIndex());
							valueCell.setCellStyle(rawCell.getCellStyle());
							setValue(valueCell, "");
						}
					}
				}
				
				rowNum++;
			} //end for
			
			sheet.shiftRows(fieldRow.getRowNum() + 1, lastRowNum + 1, -2, false, false);
		}
		else
		{
			
			/*
			 * sheet.shiftRows(rowNum, Math.max(rowNum, lastRowNum), values.size());// 移动剩余的行
			 * sheet.shiftRows(fieldRow.getRowNum() + 1, lastRowNum + 1, -2, false, false);
			 */
			
			sheet.shiftRows(rowNum, (lastRowNum > rowNum ? lastRowNum : rowNum) + 1, -2, false,
					false);
		}
		
	}
	
	@Override
	public void makeTag(int sheetIndex, String tagName, Object value)
	{
		
		System.out.println("sheet:" + sheetIndex + ",tag:" + tagName);
		
		Cell cell = findCell(wb.getSheetAt(sheetIndex), tagName);
		if (cell != null)
		{
			setValue(cell, value);
		}
	}
	
	@Override
	public void makeTag(int sheetIndex, String tagName, Collection<?> values) throws Exception
	{
		
		System.out.println("sheet:" + sheetIndex + ",tag:" + tagName);
		
		Sheet dynamicSheet = wb.getSheetAt(sheetIndex); // 屏蔽全局参数
		Integer lastRowNumInteger = lastRowNums.get(sheetIndex);
		int lastRowNum = (lastRowNumInteger == null ? dynamicSheet.getLastRowNum()
				: lastRowNumInteger); // 屏蔽全局参数
		
		Cell cell = findCell(dynamicSheet, tagName);
		
		if (cell == null)
		{
			return;
		}
		
		Row tagRow = cell.getRow();// 标记所在行
		Row fieldRow = dynamicSheet.getRow(tagRow.getRowNum() + 1);// 列表字段所在行
		int rowNum = fieldRow.getRowNum() + 1;
		if (values != null && values.size() > 0)
		{
			
			dynamicSheet.shiftRows(rowNum, Math.max(lastRowNum, rowNum), values.size());// 移动剩余的行
			
			lastRowNum = lastRowNum + values.size();
			for (Object bean : values)
			{
				Row row = dynamicSheet.createRow(rowNum);
				Iterator<Cell> rawCells = fieldRow.cellIterator();
				while (rawCells.hasNext())
				{
					Cell rawCell = rawCells.next();
					String fieldName = rawCell.getStringCellValue();
					if (fieldName != null && !"".equals(fieldName.trim()))
					{
						Cell valueCell = row.createCell(rawCell.getColumnIndex());
						valueCell.setCellStyle(rawCell.getCellStyle());
						Field field = bean.getClass().getDeclaredField(fieldName);
						field.setAccessible(true);
						Object value = field.get(bean);
						setValue(valueCell, value);
					}
					else
					{
						Cell valueCell = row.createCell(rawCell.getColumnIndex());
						valueCell.setCellStyle(rawCell.getCellStyle());
						setValue(valueCell, "");
					}
				}
				rowNum++;
			}
			
			dynamicSheet.shiftRows(fieldRow.getRowNum() + 1, lastRowNum + 1, -2, false, false);
			
		}
		else
		{
			
			dynamicSheet.shiftRows(rowNum, Math.max(lastRowNum, rowNum), 0);// 移动剩余的行
			dynamicSheet.shiftRows(fieldRow.getRowNum() + 1, lastRowNum + 1, -2, false, false);
			
			/*
			 * dynamicSheet.shiftRows(rowNum, (lastRowNum > rowNum ? lastRowNum : rowNum) + 1, -2,
			 * false, false);
			 */
		}
		
		lastRowNums.put(sheetIndex, lastRowNum);
	}
	
	@Override
	public void close()
	{
		wb = null;
		System.gc();
	}
	
	// 根据标记名称查找单元格
	private Cell findCell(Sheet sheet, String tag)
	{
		if (tag != null)
		{
			tag = "#" + tag + "#";
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext())
			{
				Row row = rows.next();
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext())
				{
					Cell cell = cells.next();
					if (cell.getCellType() == Cell.CELL_TYPE_STRING)
					{
						String value = cell.getStringCellValue();
						if (tag.equals(value.trim()))
							return cell;
					}
				}
			}
		}
		return null;
	}
	
	// 设置单元格内容
	private void setValue(Cell cell, Object value)
	{
		if (value instanceof Number)
		{
			Number num = (Number) value;
			cell.setCellValue(num.doubleValue());
		}
		else
			cell.setCellValue(value == null ? "" : value.toString());
	}
	
	@Override
	public File saveAs(String abspath) throws Exception
	{
		FileOutputStream out = null;
		try
		{
			File file = new File(abspath);
			out = new FileOutputStream(file);
			wb.write(out);
			return file;
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (out != null)
				out.close();
		}
	}
	
	/**
	 * 创建临时文件名称
	 * 
	 * @param postfix
	 *            后缀名
	 * @return 文件名称
	 */
	public static String createTempFileName(String postfix)
	{
		return UUID.randomUUID() + "." + postfix;
	}
	
	@Override
	public void renameSheet(int sheetIndex, String sheetName)
	{
		
		wb.setSheetName(sheetIndex, sheetName);
		
	}
	
	public Sheet getSheet()
	{
		return sheet;
	}

	@Override
	public Workbook getWorkbook()
	{
		return wb;
	}
}
