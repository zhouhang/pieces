package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.dao.OrganizationDao;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Organization;
import com.jointown.zy.common.service.OrganizationService;
/**
 * 组织机构ServiceImpl
 * @author zhouji
 * 2014年11月6日下午4:54:00
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private BossUserDao bossUserDao;
	
	@Override
	public void addOrganization(Organization organization) {
		Date date = new Date();
		organization.setStatus(0);
		organization.setCreateTime(date);
		organization.setUpdateTime(date);
		organizationDao.addOrganization(organization);
	}

	@Override
	public void deleteOrganization(Organization organization) {
		organizationDao.deleteOrganization(organization);
	}

	@Override
	public void updateOrganization(Organization organization) {
		organizationDao.alterOrganization(organization);
	}

	@Override
	public List<Organization> getAllOrganization() {
		return organizationDao.getAllOrganization();
	}

	@Override
	public String getOrganizationName(Integer id) {
		Organization org = organizationDao.getOrganizationById(id);
		return org.getOrgName();
	}

	@Override
	public String getParentsOrgName(Integer id) {
		String porgName = "";
		Organization org = organizationDao.getOrganizationById(id);
		String orgName = org.getOrgName();
		while (org.getParentId()!=0) {
			Organization org1 = organizationDao.getOrganizationById(org.getParentId());
			porgName = org1.getOrgName()+"/"+porgName;
			org=org1;
		}
		porgName = porgName+orgName;
		return porgName;
	}

	@Override
	public String validateOrgName(String orgName) {
		String msg = "";
		Organization org = organizationDao.getOrganizationByOrgName(orgName);
		if(org==null){
			//组织名未被占用
			msg="y";
		}else{
			if(org.getStatus()==0){
				//组织名被占用
				msg="该组织名被占用,请重新输入!";
			}else{
				//组织名未被占用
				msg="y";
			}
		}
		return msg;
	}

	@Override
	public Organization getOrganizationById(Integer orgId) {
		return organizationDao.getOrganizationById(orgId);
	}

	@Override
	public Boolean validateOrgChildren(Integer pId) {
		List<Organization> orglist = organizationDao.getOrganizationByPid(pId);
		List<BossUser> bulist = bossUserDao.getBossUsersByOrgId(pId);
		if(orglist.isEmpty()&&bulist.isEmpty()){
			//为空,没有子集
			return true;
		}else{
			//不为空,有子集
			return false;
		}
	}

	@Override
	public Organization getOrganizationByOrgName(String orgName) {
		return organizationDao.getOrganizationByOrgName(orgName);
	}

	@Override
	public String getOrgParentName(Integer orgId) throws Exception {
	        //和数据库交互,得到当前节点记录  
		Organization organ = organizationDao.getOrganizationById(orgId);  
        if(organ != null){  
            if(organ.getParentId()!=null && organ.getParentId().intValue()!=organ.getId().intValue()){//存在父节点的情况
            	//当前节点的父节点ID  
	            return getOrgParentName(organ.getParentId())+"/"+organ.getOrgName(); 
            }else{//已经是父节点的情况
            	return organ.getOrgName();
            }
        }else{  
            return "";  
        }  
	}

}
