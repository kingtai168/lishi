package com.lishi.recruit.excel.writer.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BeanInitUtil {

	/**
	 * 根据类的Class生成一个包含多个由内容对象的LIST
	 * 
	 * @param clazz
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> initList(Class<T> clazz, int size)
			throws Exception {
		if (size <= 0) {
			return null;
		}
		//
		List<T> lst = new ArrayList<T>();
		for (int i = 0; i < size; i++) {
			T obj = init(clazz, i + 1);
			lst.add(obj);
		}

		return lst;

	}

	/**
	 * 根据类的Class生成一个有内容的对象
	 * 
	 * @param clazz
	 * @param num
	 * @return
	 * @throws Exception
	 */
	public static <T> T init(Class<T> clazz, int num) throws Exception {
		// 声明实例
		T bean = clazz.newInstance();
		// 获得其所有字段
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			// 获得一个
			Field field = fields[i];
			// 如果是字符串才进行内容设置
			if (String.class == field.getType()) {
				try {

					field.setAccessible(true);
					String fieldName = field.getName();
					field.set(bean, fieldName + num);

				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			} else if (int.class == field.getType() || Integer.class == field.getType()) {
					try {

						field.setAccessible(true);
						field.set(bean,  i * 100 + num);

					} catch (Exception e) {
						System.out.println(e);
						continue;
					}
			}
		}

		return bean;
	}

	public static void main(String[] args) throws Exception {

	}

}
