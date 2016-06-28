package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.Organization;

/**
 * 组织机构Dao
 * @author zhouji
 * 2014年11月6日下午4:46:20
 */
public interface OrganizationDao {
	/**
	 * 新增组织机构 
	 * @author zhouji
	 * @param organization
	 */
	public void addOrganization(Organization organization);
	/**
	 * 删除组织机构
	 * @author zhouji
	 * @param organization
	 */
	public void deleteOrganization(Organization organization);
	/**
	 * 修改组织机构
	 * @author zhouji
	 * @param organization
	 */
	public void alterOrganization(Organization organization);
	/**
	 * 获取所有组织机构对象
	 * @author zhouji
	 * @return
	 */
	public List<Organization> getAllOrganization();
	/**
	 * 根据id获取组织名称
	 * @author zhouji
	 * @return
	 */
	public Organization getOrganizationById(Integer id);
	/**
	 * 根据pid获取组织
	 * @author zhouji
	 * @return
	 */
	public List<Organization> getOrganizationByPid(Integer pId);
	/**
	 * 根据组织名称获取组织对象
	 * @author zhouji
	 * @return
	 */
	public Organization getOrganizationByOrgName(String orgName);
}
