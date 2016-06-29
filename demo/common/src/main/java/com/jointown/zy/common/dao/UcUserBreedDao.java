package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.UcUserBreed;

/**
 * @ClassName: UcUserBreedDao
 * @Description: 经营品种Dao
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
public interface UcUserBreedDao {
	
	/**
	 * @Description: 添加经营品种
	 * @Author: ldp
	 * @Date: 2015年10月18日
	 * @param ucUserBreedDao
	 * @return
	 * @throws Exception
	 */
	int addUcUserBreed(UcUserBreed ucUserBreed) throws Exception;
	
	/**
	 * @Description: 逻辑删除用户下品种
	 * @Author: Calvin.wh
	 * @Date: 2015-10-21
	 * @param ucUserBreed
	 * @return
	 * @throws Exception
	 */
	public int delUcUserBreed(UcUserBreed ucUserBreed) throws Exception;
	
	/**
	 * @Description: 根据用户id获取经营品种
	 * @Author: Calvin.wh
	 * @Date: 2015-10-21
	 * @return
	 * @throws Exception
	 */
	public List<UcUserBreed> getUcUserBreed(UcUserBreed ucUserBreed) throws Exception;

}
