package com.lishi.recruit.excel.writer.utils;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象转换
 * 
 * @author wmchen
 * @version 1.0
 * @since  2015-6-26
 *
 */
public class Bean2MAP
{
	
	/**
	 * 将二位LIST转化为LIST<MAP>表示的结构
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> tran2DList2Map(List<List<Object>> rows)
			throws Exception
	{
		
		if (rows == null || rows.size() == 0)
		{
			return null;
		}
		
		List<Map<String, Object>> rowDatas = new ArrayList<Map<String, Object>>();
		
		for (List<?> row : rows)
		{
			
			Map<String, Object> rowMap = new HashMap<String, Object>();
			
			int size = row.size();
			
			for (int i = 0; i < size; i++)
			{
				rowMap.put("col" + i, row.get(i));
			}
			
			rowDatas.add(rowMap);
			
		}
		
		return rowDatas;
	}
	
	/**
	 * 将一个BEAN转化为用MAP表示的结构
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> tran2Map(Object... bean) throws Exception
	{
		
		Map<String, Object> rootMap = new HashMap<String, Object>();
		
		for (int i = 0; i < bean.length; i++)
		{
			
			addObject2Map(bean[i], rootMap);
		}
		
		return rootMap;
	}
	
	/**
	 * @param bean
	 * @param rootMap
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> addObject2Map(Object bean, Map<String, Object> rootMap)
			throws Exception
	{
		
		// 获得所有属性
		Field[] fields = bean.getClass().getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++)
		{
			
			Field filed = fields[i];
			filed.setAccessible(true);
			Object value = filed.get(bean);
			System.out.print(filed.getType());
			rootMap.put(filed.getName(), value);
		}
		
		return rootMap;
	}
	
	/**
	 * 带前缀的将bean数据加入到map中，该方法方便于不同的列表对象做组合
	 * 
	 * @param prefix
	 * @param bean
	 * @param rootMap
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> addObject2Map(String prefix, Object bean,
			Map<String, Object> rootMap) throws Exception
	{
		
		if (prefix == null || "".equals(prefix.trim()))
		{
			addObject2Map(bean, rootMap);
		}
		
		// 获得所有属性
		Field[] fields = bean.getClass().getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++)
		{
			
			Field filed = fields[i];
			filed.setAccessible(true);
			Object value = filed.get(bean);
			
			rootMap.put(prefix + "." + filed.getName(), value);
		}
		
		return rootMap;
	}

	/**
	 * 过滤long为零的数
	 * @param bean
	 * @param rootMap
	 * @param isFilter
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> addObject2Map(Object bean, Map<String, Object> rootMap,boolean isFilter)
			throws Exception
	{

		// 获得所有属性
		Field[] fields = bean.getClass().getDeclaredFields();

		for (int i = 0; i < fields.length; i++)
		{

			Field filed = fields[i];
			filed.setAccessible(true);
			Object value = filed.get(bean);
			if(isFilter==true&&filed.getType().getName().equals("long")&&(Long)value==0){
				rootMap.put(filed.getName(), "");
			}else {
				rootMap.put(filed.getName(), value);
			}

		}

		return rootMap;
	}
}
