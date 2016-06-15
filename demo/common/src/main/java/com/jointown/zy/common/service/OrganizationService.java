package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.Organization;

/**
 * 组织机构Service
 * @author zhouji
 * 2014年11月6日下午4:53:44
 */
public interface OrganizationService {
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
	public void updateOrganization(Organization organization);
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
	public String getOrganizationName(Integer id);
	/**
	 * 根据id获取父级组织名称
	 * @author zhouji
	 * @return
	 */
	public String getParentsOrgName(Integer id);
	/**
	 * 验证组织名称是否存在
	 * @author zhouji
	 * @return
	 */
	public String validateOrgName(String orgName);
	/**
	 * 根据id获取组织对象
	 * @author zhouji
	 * @return
	 */
	public Organization getOrganizationById(Integer orgId);
	/**
	 * 根据name获取组织对象
	 * @param orgName
	 * @return
	 */
	public Organization getOrganizationByOrgName(String orgName);
	
	/**
	 * 验证组织是否有子集
	 * @author zhouji
	 * @param orgId
	 * @return
	 */
	public Boolean validateOrgChildren(Integer pId);
	
	/** 
	 * @author fanyuna
	 * 2015.07.29
     * 递归得到当前节点的所有父节点 
     * @param orgId 当前节点 
     * @return 所有父节点 
     * @throws Exception 抛出的异常 
     */  
    public String getOrgParentName(Integer orgId)throws Exception;
}
