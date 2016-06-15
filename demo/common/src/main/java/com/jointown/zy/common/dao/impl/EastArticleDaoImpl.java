/**
 * @author guoyb
 * 2015年3月3日 上午9:40:01
 */
package com.jointown.zy.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.EastArticleDao;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.EastArticleVo;

/**
 * @author guoyb
 * 2015年3月3日 上午9:40:01
 */
@Repository
public class EastArticleDaoImpl extends BaseDaoImpl implements EastArticleDao {

	/* (non-Javadoc)
	 * @see com.jointown.zy.common.dao.EastArticleDao#selectEastArticleByLMId(java.lang.Integer)
	 */
	@Override
	public List<EastArticleVo> selectEastArticleByLMId(Integer lmid) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.EastArticleMapper.selectEastArticleByLMId",lmid);
	}

	
	/* (non-Javadoc)
	 * @see com.jointown.zy.common.dao.EastArticleDao#selectEastArticleByACid(java.lang.Integer)
	 */
	@Override
	public EastArticleVo selectEastArticleByACid(Integer acid) {
		return this.getSqlSession().selectOne("com.jointown.zy.common.dao.EastArticleMapper.selectEastArticleByACid",acid);
	}

	
	/* (non-Javadoc)
	 * @see com.jointown.zy.common.dao.EastArticleDao#selectEastArticlPagedByLMId(com.jointown.zy.common.model.Page)
	 */
	@Override
	public List<EastArticleVo> selectEastArticlePagedByLMId(
			Page<Map<String, Object>> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.EastArticleMapper.selectEastArticlePagedByLMId",page);
	}


	@Override
	public List<EastArticleVo> selectEastArticleNewsByCondition(
			Page<EastArticleVo> page) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.EastArticleMapper.selectEastArticleNewsByCondition",page);
	}


	@Override
	public int updateEastArticleTipByACid(Integer acid) {
		return this.getSqlSession().update("com.jointown.zy.common.dao.EastArticleMapper.updateEastArticleTipByACid", acid);
	}
}
