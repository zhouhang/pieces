package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.UcUserBreed;
import com.jointown.zy.common.model.UcUserScope;

/**
 * @ClassName: UcUserScopeDao
 * @Description: 经营范围信息Dao
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
public interface UcUserScopeDao {

	/**
	 * @Description: 新增经营范围信息
	 * @Author: ldp
	 * @Date: 2015年10月18日
	 * @param ucUserScope
	 * @return
	 */
	int addUcUserScope(UcUserScope ucUserScope) throws Exception;
	
	/**
	 * @Description: 品种模糊查询
	 * @Author: Calvin.wh
	 * @Date: 2015-10-19
	 * @return 返回品种id 品种名称
	 */
	public List<Breed> getBreeds(String param);
	
	
	/**
	 * @Description: 品种查询
	 * @Author: ff
	 * @Date: 2015-10-20
	 * @return 返回品种id 品种名称
	 */
	public List<UcUserBreed> getBreedsByUserId(Long userId);
	
	/**
	 * @Description: 根据会员ID查询经营信息
	 * @Author: ldp
	 * @Date: 2015年10月19日
	 * @param scopeId
	 * @return
	 * @throws Exception
	 */
	UcUserScope selectUcUserScopeById(Long userId) throws Exception;
	
	/**
	 * @Description: 修改经营信息
	 * @Author: ldp
	 * @Date: 2015年10月19日
	 * @param ucUserScope
	 * @return
	 * @throws Exception
	 */
	int updateUcUserScope(UcUserScope ucUserScope) throws Exception;
	
}
