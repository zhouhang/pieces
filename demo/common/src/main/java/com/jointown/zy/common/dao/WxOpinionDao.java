package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxOpinion;
import com.jointown.zy.common.vo.WxOpinionVo;

/**
 * 意见反馈
 *
 * @author aizhengdong
 *
 * @data 2015年7月13日
 */
public interface WxOpinionDao {

	/**
	 * 添加记录
	 *
	 * @param wxOpinion
	 * @return PrimaryKey
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月13日
	 */
	int insert(WxOpinion wxOpinion);
	
	/**
	 * 更新不为NULL字段信息
	 *
	 * @param wxOpinion
	 * @return 更新记录的条数
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月13日
	 */
	int updateByPrimaryKeySelective(WxOpinion wxOpinion);
	
	/**
	 * 删除记录
	 *
	 * @author aizhengdong
	 * @date 2015年8月27日
	 */
	int deleteByPrimaryKey(Long opId);

	/**
	 * 根据ID获取记录
	 *
	 * @param opId
	 * @return WxOpinion
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月13日
	 */
	WxOpinion selectByPrimaryKey(Long opId);

	/**
	 * 获取满足条件的记录
	 *
	 * @param page
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年7月13日
	 */
	List<WxOpinion> selectWxOpinionByCondition(Page<WxOpinionVo> page);
}
