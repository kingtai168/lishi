package com.lishi.recruit.excel.writer.service.impl;

import java.io.File;
import java.util.Map;

import org.springframework.util.FileCopyUtils;

import com.lishi.recruit.excel.writer.service.DataReadyCallback;
import com.lishi.recruit.excel.writer.service.ExcelWriter;


/**
 * 数据写入完成后的回调，用于做如单元格合并等操作
 * 
 * @author wmchen
 * @version 1.0
 * @since  2015-6-26
 *
 */
public class ExcelPoiWriterImpl implements ExcelWriter
{
	
	@Override
	public File genarateFile(String templateFiePath, String targetFilePath,
			Map<String, Object> data, DataReadyCallback callback) throws Exception
	{
		
		// 如模板存在
		File templateFile = new File(templateFiePath);
		if (templateFile.exists())
		{
			// 生成的文件
			File gFile = null;
			
			// 将模板复制到转移到临时路径，
			File tmpFile = new File(targetFilePath);
			FileCopyUtils.copy(templateFile, tmpFile);
			templateFile = tmpFile;
			
			ExcelTemplateManager manager = null;
			
			try
			{
				manager = new PoiTemplateManager();
				manager.open(templateFile);
				
				// 处理数据
				manager.makeTag(data);
				
				// 回调
				if (callback != null)
				{
					callback.dressUp(manager);
				}
				
				// 保存文件
				gFile = manager.saveAs(targetFilePath);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				if (manager != null)
					manager.close();
			}
			
			return gFile;
		}
		else
		{
			throw new Exception("模板文件不存在：" + templateFiePath);
		}
	}
	
	@Override
	public File genarateFile(String templateFiePath, String targetFilePath, Map<String, Object> data)
			throws Exception
	{
		return this.genarateFile(templateFiePath, targetFilePath, data, null);
	}
	
}
