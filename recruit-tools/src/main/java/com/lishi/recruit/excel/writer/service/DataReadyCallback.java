package com.lishi.recruit.excel.writer.service;

import com.lishi.recruit.excel.writer.service.impl.ExcelTemplateManager;


/**
 * 数据写入完成后的回调，用于做如单元格合并等操作
 * 
 * @author wmchen
 * @version 1.0
 * @since  2015-6-26
 *
 */
public interface DataReadyCallback
{
    
    /**
     * 在数据均设置成功后的回调，方便上层做进一步处理 如合并单元格
     * 
     * @param tm
     * @throws Exception
     */
    void dressUp(ExcelTemplateManager tm) throws Exception;
    
}
