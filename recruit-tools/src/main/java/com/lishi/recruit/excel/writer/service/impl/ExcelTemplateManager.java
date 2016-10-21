package com.lishi.recruit.excel.writer.service.impl;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * EXCEL文件操作管理类
 * 
 * @author wmchen
 * @version 1.0
 * @since  2015-6-26
 *
 */
public interface ExcelTemplateManager
{
	
	/**
	 * 打开模板，生成文件必须通过close()方法关闭文件
	 * 
	 * @param templeateFile
	 *            模板文件
	 * @throws Exception
	 */
	void open(File templeateFile) throws Exception;
	
	/**
	 * 生成列表标志
	 * 
	 * @param data
	 * @throws Exception
	 */
	void makeTag(Map<String, Object> data) throws Exception;
	
	/**
	 * 生成标记值
	 * 
	 * @param tagName
	 *            标记名称
	 * @param value
	 *            值
	 */
	void makeTag(String tagName, Object value);
	
	/**
	 * 生成列表标记值
	 * 
	 * @param tagName
	 *            标记名称
	 * @param values
	 *            值
	 * @throws Exception
	 */
	void makeTag(int sheetIndex, String tagName, Collection<?> values) throws Exception;
	
	/**
	 * 生成标记值
	 * 
	 * @param tagName
	 *            标记名称
	 * @param value
	 *            值
	 */
	void makeTag(int sheetIndex, String tagName, Object value);
	
	/**
	 * 生成列表标记值
	 * 
	 * @param tagName
	 *            标记名称
	 * @param values
	 *            值
	 * @throws Exception
	 */
	void makeTag(String tagName, Collection<?> values) throws Exception;
	
	/**
	 * 保存文件
	 * 
	 * @param abspath
	 *            保存路径
	 * @return 生成后的文件
	 * @throws Exception
	 */
	File saveAs(String abspath) throws Exception;
	
	/**
	 * 重命名sheet
	 * 
	 * @param sheetIndex
	 * @param sheetName
	 */
	void renameSheet(int sheetIndex, String sheetName);
	
	/**
	 * 关闭
	 */
	void close();
	
	/**
	 * 获取表单
	 * 
	 * @return
	 */
	Sheet getSheet();
	
	/**
	 * 获取工作簿
	 * 
	 * @return
	 */
	Workbook getWorkbook();
}
