package com.lishi.recruit.excel.writer.service;

import java.io.File;
import java.util.Map;



/**
 * 生成EXCEL文件
 * 
 * @author wmchen
 * @version 1.0
 * @since  2015-6-26
 *
 */
public interface ExcelWriter
{
	
	/**
	 * @param templateFiePath
	 *            模板文件路径
	 * @param targetFilePath
	 *            目标文件路径
	 * @param data
	 *            所有的数据
	 * @throws Exception
	 */
	File genarateFile(String templateFiePath, String targetFilePath, Map<String, Object> data)
			throws Exception;
	
	/**
	 * @param templateFiePath
	 *            模板文件路径
	 * @param targetFilePath
	 *            目标文件路径
	 * @param data
	 *            所有的数据
	 * @param callback
	 *            当所有数据均处理完毕后，回调该callback供上层做其它处理（如格式、合并单元格等）
	 * @throws Exception
	 */
	File genarateFile(String templateFiePath, String targetFilePath, Map<String, Object> data,
			DataReadyCallback callback) throws Exception;
	
}
