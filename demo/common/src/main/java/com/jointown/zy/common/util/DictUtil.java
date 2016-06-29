package com.jointown.zy.common.util;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.jointown.zy.common.dao.BaseDaoImpl;
/**
 * 字典表工具类
 * @author chengchang
 * @param <T>
 */
public class DictUtil  {
	/**
	 * @author chengchang
	 * @param DICT_TYPE
	 * @description 输入字典表中的DICT_TYPE,查询出相同的DICT_TYPE封装成list返回在页面上
	 * @return
	 */
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	public static  List getDictList(String DICT_TYPE){
		return null;
	}
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}
