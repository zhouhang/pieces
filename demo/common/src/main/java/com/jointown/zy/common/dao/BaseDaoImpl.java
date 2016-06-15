package com.jointown.zy.common.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author kevinzhou
 * @date 2014-09-19 12:15:56
 */
public class BaseDaoImpl  implements InitializingBean{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

}