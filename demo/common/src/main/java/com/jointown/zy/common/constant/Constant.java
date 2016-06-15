package com.jointown.zy.common.constant;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取常量列表类
 * @ClassName:Constant
 * @author:Calvin.Wangh
 * @Description:
 * @date:2015-5-15上午11:09:51
 * @version V1.0
 */
public class Constant {
	public static class getConstantResult {
		public static List getConstantResult(String constantName,String resultList) {
			List list = null;
			constantName = Constant.class.getName() + "$"+ constantName;
			try {
				Object[] args = new Object[2];
				args[0] = constantName;
				args[1] = resultList;
				Class[] classes = new Class[2];
				classes[0] = String.class;
				classes[1] = String.class;
				Class constantClass = Class.forName(constantName);
				Method method = constantClass.getMethod("showList", classes);
				list = (List) method.invoke(constantClass, args);
			} catch (ClassNotFoundException e) {
				return null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		/**
		 * @param constantName 常量类类名
		 * @param channel	         常量类频道 比如PayChannel支付频道
		 * @param resultList	
		 * @return
		 */
		public static final List getList(String constantName,String channel, String resultList) {
			List list = new ArrayList();
			constantName = constantName + "$"+ channel;
			try {
				Class constantClass = Class.forName(constantName);
				if (resultList != null && resultList.length() > 0) {
					String[] sKey = resultList.split(",");
					for (int i = 0; i < sKey.length; i++) {
						Object[] args = new Object[1];
						Field[] field = constantClass.getDeclaredFields();
						Method method = null;
						if (field.length > 0) {
							// 确认第一个变量的值的类型即可
							String typeName = field[0].getType().getName();
							if (typeName.equals("int")) {
								args[0] = Integer.valueOf(sKey[i]);
								method = constantClass.getMethod("getName",int.class);
							} else if (typeName.equals("char")) {
								args[0] = sKey[i];
								method = constantClass.getMethod("getName",char.class);
							} else {
								args[0] = sKey[i];
								method = constantClass.getMethod("getName",String.class);
							}
						}

						String name = (String) method.invoke(constantClass,args);
						if (name != null && !"".equals(name)) {
							ConstantPO constantPO = new ConstantPO();
							constantPO.setKey(sKey[i]);
							constantPO.setName(name);
							list.add(constantPO);
						}

					}
				} else {
					Field[] field = constantClass.getDeclaredFields();
					if (field.length > 0) {
						for (int i = 0; i < field.length; i++) {
							ConstantPO constantPO = new ConstantPO();
							Method method = null;
							Object key = field[i].get(constantClass);
							Object[] args = new Object[1];
							args[0] = key;

							String typeName = field[i].getType().getName();
							if (typeName.equals("int")) {
								method = constantClass.getMethod("getName",int.class);
							} else if (typeName.equals("char")) {
								method = constantClass.getMethod("getName",char.class);
							} else {
								method = constantClass.getMethod("getName",key.getClass());
							}
							String name = (String) method.invoke(constantClass,args);

							if (name != null && !"".equals(name)) {
								constantPO.setKey(String.valueOf(key));
								constantPO.setName(name);
								list.add(constantPO);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

	}
}
