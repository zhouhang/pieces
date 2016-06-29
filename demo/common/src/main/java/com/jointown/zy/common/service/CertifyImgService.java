package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.BossUserRole;
import com.jointown.zy.common.model.CertifyImg;
import com.jointown.zy.common.model.CompanyCertify;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BossUserOrgRoleVO;

public interface CertifyImgService {
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
	 * 查询认证表关联的图片
	 * @author zhouji
	 * @param certifyId
	 * @return
	 */
	public List<CertifyImg> findCertifyImgByCertifyId(Integer certifyId);
	/**
	 *  查询认证表关联的图片
	 * @author biran
	 * @param certifyId,type
	 * @return
	 */
	public CertifyImg findCertifyImgByCertifyIdAndType(Integer certifyId,Integer type);

}
