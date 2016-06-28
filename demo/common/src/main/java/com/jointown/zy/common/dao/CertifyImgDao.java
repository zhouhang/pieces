package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.CertifyImg;
/**
 * 认证图片
 * @author zhouji
 *
 * 2015年1月5日
 */
public interface CertifyImgDao {
	/**
	 * 上传保存图片
	 * @author zhouji
	 * @param certifyImg
	 */
	public void addCertifyImg(CertifyImg certifyImg);
	/**
	 * 修改
	 * @author zhouji
	 * @param certifyImg
	 */
	public void updateCertifyImg(CertifyImg certifyImg);
	/**
	 * 逻辑删除
	 * @author zhouji
	 * @param certifyImg
	 */
	public void updateAllCertifyImg(Integer certifyId);
	/**
	 * 查询认证表关联的图片
	 * @author zhouji
	 * @param certifyId
	 * @return
	 */
	public List<CertifyImg> findCertifyImgByCertifyId(Integer certifyId);
	/**
	 * 查询认证表关联的图片
	 * @author biran
	 * @param certifyId,type
	 * @return
	 */
	public CertifyImg findCertifyImgByCertifyIdAndType(Integer certifyId,Integer type);
	
	/**
	 * 更新对应图片的状态
	 * @author ldp
	 * @param certifyId
	 * @param type
	 * @param smallType
	 * @param bigType
	 * @return
	 */
	public int updateAllTypeCertifyImg(Integer certifyId,Integer type,Integer smallType,Integer bigType);
	
	/**
	 * 根据认证ID、图片类型查询图片
	 * @param certifyId
	 * @param type
	 * @param smallType
	 * @param bigType
	 * @return
	 */
	public List<CertifyImg> findAllTypeImgByCertifyId(Integer certifyId,Integer type,Integer smallType,Integer bigType);
	
}
