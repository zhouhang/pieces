package com.pieces.service.utils;

import java.util.List;
import java.util.Map;

/**
 * 验证工具集
 * @author feng
 *
 */
public class ValidUtils {
	/**
	 * 验证list是否为空
	 * @param list
	 * @return
	 */
	public static boolean listBlank(List list){
		return (list != null && list.size() != 0);
	}
	
	/**
	 * 验证map是否为空
	 * @param list
	 * @return
	 */
	public static boolean mapBlank(Map map){
		return (map != null && !map.isEmpty());
	}

}
