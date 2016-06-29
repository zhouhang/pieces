package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.dao.IBossUserOrgRoleDao;
import com.jointown.zy.common.dao.OrganizationDao;
import com.jointown.zy.common.dto.Password;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.BossUserOrg;
import com.jointown.zy.common.model.BossUserRole;
import com.jointown.zy.common.model.Organization;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BossUserService;
import com.jointown.zy.common.util.EncryptUtil;
import com.jointown.zy.common.util.GetInitPassword;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.MessageSend;
import com.jointown.zy.common.vo.BossUserOrgRoleVO;


/**
 * @ClassName: BossUserServiceImpl
 * @Description: 后台账号管理
 * @Author: 宋威
 * @Date: 2015年4月28日
 * @Version: 1.0
 */
@Service
public class BossUserServiceImpl implements BossUserService {
	//add byMr.song 2015.4.28 13:46 log日志
	private static final Logger log = LoggerFactory.getLogger(BossUserServiceImpl.class);
	
	@Autowired
	private BossUserDao bossUserDao;
	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private IBossUserOrgRoleDao bossUserOrgRoleDao;
	@Autowired
	public MessageConfigManage messageConfigManage;
	@Autowired
	public ThreadPoolTaskExecutor taskExecutor;	
	
	@Override
	public void addBossUser(BossUser bossUser) {
		Date date = new Date();
		Password pass = EncryptUtil.JointownEncode(bossUser.getPassword());
		bossUser.setPassword(pass.getPassword());
		bossUser.setSalt(pass.getSalt());
		bossUser.setStatus(0);
		bossUser.setCreateTime(date);
		bossUser.setUpdateTime(date);
		bossUserDao.addBossUser(bossUser);
		//插入用户角色表
		if(bossUser.getRoleId().length()!=0&&bossUser.getRoleId()!=null){
			BossUserRole bossUserRole = new BossUserRole();
			BossUser bs = bossUserDao.findBossUserByUserCode(bossUser.getUserCode());
			bossUserRole.setUserId(bs.getUserId());
			String[] roleIds = bossUser.getRoleId().split(",");
			for (int i = 0; i < roleIds.length; i++) {
				bossUserRole.setRoleId(Integer.parseInt(roleIds[i]));
				bossUserRole.setStatus(0);
				bossUserRole.setCreateTime(date);
				bossUserRole.setUpdateTime(date);
				bossUserDao.addBossUserRole(bossUserRole);
			}
		}
	}

	@Override
	public void deleteBossUser(BossUser bossUser) {
		Date date = new Date();
		bossUser.setUpdateTime(date);
		bossUserDao.deleteBossUser(bossUser);
	}
	
	/**
	 * @Description: 后台-锁定工作账号
	 * @param BossUser bossUser<命名不规范> 表示前端传过来的界面DTO对象，即用户填写用户的基本参数
	 * @Author: 宋威
	 * @Date: 2015年4月28日 15:19
	 */
	@Override
	public void updateBossUserByLock(BossUser bossUser) {
		/**
		 * add by Mr.song 2015.4.28 13:48 添加前台界面对象DTO非空判断逻辑
		 */
		if(bossUser==null){
			log.info("BossUserServiceImpl.updateBossUser【object empty】");
			return ;
		}
		BossUser buser = bossUserDao.findBossUserByUserCode(bossUser.getUserCode());
		if(bossUser.getMobile()!=null){
			buser.setMobile(bossUser.getMobile());
		}
		if(bossUser.getUserName()!=null){
			buser.setUserName(bossUser.getUserName());
		}
		if(bossUser.getPassword()!=null){
			buser.setPassword(bossUser.getPassword());
		}
		if(bossUser.getOrgId()!=null){
			buser.setOrgId(bossUser.getOrgId());
		}
		if(bossUser.getStatus()!=null){
			if(bossUser.getStatus()==0){
				buser.setStatus(3);
			}else if(bossUser.getStatus()==3){
				buser.setStatus(0);
			}else{
				buser.setStatus(bossUser.getStatus());
			}
		}
		Date date = new Date();
		buser.setUpdateTime(date);
		bossUserDao.alterBossUser(buser);
	}
	
	
	/**
	 * @Description: 后台-修改工作账号（包括修改用户-角色关系表对应关系，如果是删除角色则使用的软删除）
	 * @param BossUser bossUser<命名不规范> 表示前端传过来的界面DTO对象，即用户填写用户的基本参数
	 * @Author: 宋威
	 * @Date: 2015年4月28日 15:19
	 */
	@Override
	public void updateBossUser(BossUser bossUser) {
		/**
		 * add by Mr.song 2015.4.28 13:48 添加前台界面对象DTO非空判断逻辑
		 */
		if(bossUser==null){
			log.info("BossUserServiceImpl.updateBossUser【object empty】");
			return ;
		}
		BossUser buser = bossUserDao.findBossUserByUserCode(bossUser.getUserCode());
		if(bossUser.getMobile()!=null){
			buser.setMobile(bossUser.getMobile());
		}
		if(bossUser.getUserName()!=null){
			buser.setUserName(bossUser.getUserName());
		}
		if(bossUser.getPassword()!=null){
			buser.setPassword(bossUser.getPassword());
		}
		if(bossUser.getOrgId()!=null){
			buser.setOrgId(bossUser.getOrgId());
		}
		Date date = new Date();
		buser.setUpdateTime(date);
		bossUserDao.alterBossUser(buser);

		/**
		 * add by Mr.song 2015.4.28 13:42解决后台-工作账号中数据库中原有角色跟用户界面选择的尚未保存的角色的新增、删除问题。
		 * 分3种情况来处理问题：
		 * 1.用户在前台取消或不填任何任何角色信息，此时则要删除角色；
		 * 2.如果数据库尚未保存角色，则前台用户选择角色，这新增用户选择的角色信息；
		 * 3.如果以上2条都不满足，则用户选择的角色信息跟数据库中此用户已有角色存在交叉情况，则要分别取差集。
		 */
		List<BossUserRole> rolelist = bossUserOrgRoleDao.selectBossUserRole(String.valueOf( buser.getUserId()));
		//先遍历前端传过来的角色ID字符串
		String[] roles = StringUtils.isNotEmpty(bossUser.getRoleId())?bossUser.getRoleId().split(","):new String[0];
		if(StringUtils.isEmpty(bossUser.getRoleId()) && rolelist.isEmpty()){//如果前端传过来的角色为空 （锁定，解锁）
			//什么都不做
		}else if(rolelist.isEmpty()){//如果前端传过来的角色不为空 ,账号已有的有效角色为空 （修改）
			//添加前端有效角色
			for (String role :roles) {
				BossUserRole bossUserRole = new BossUserRole();
				bossUserRole.setUserId(buser.getUserId());
				bossUserRole.setRoleId(Integer.parseInt(role));
				bossUserRole.setStatus(0);
				bossUserRole.setCreateTime(date);
				bossUserRole.setUpdateTime(date);
				bossUserDao.addBossUserRole(bossUserRole);
			}
		}else{//如果前端传过来的角色不为空 ,账号已有的有效角色也不为空，则前端值跟后台数据库的存在差异的情况 （修改）
			List<Integer> c_list=new ArrayList<Integer>();   //前端用户选择
			List<Integer> s_list=new ArrayList<Integer>();   //后台数据库已保存
			List<Integer> t_list=new ArrayList<Integer>();   //临时保存前端用户选择
	        for(int i=0;i<roles.length;i++){  
	        	c_list.add(Integer.parseInt(roles[i]));  
	        	t_list.add(Integer.parseInt(roles[i]));  
	        }
	        for (int j = 0; j < rolelist.size(); j++) {
				BossUserRole bossUserRole = rolelist.get(j);
				s_list.add(bossUserRole.getRoleId());
			}
	        c_list.removeAll(s_list); //取差集 获取用户新增的角色
	        //前台用户选择剔除了后台重复的数据，然后insert到数据库
			for (int k = 0; k < c_list.size(); k++) {
				BossUserRole bossUserRole = new BossUserRole();
				bossUserRole.setUserId(buser.getUserId());
				bossUserRole.setRoleId(c_list.get(k));
				bossUserRole.setStatus(0);    //add by Mr.song 2015.4.28 15:12 ,1表示软删除，0表示有效
				bossUserRole.setCreateTime(date);
				bossUserRole.setUpdateTime(date);
				bossUserDao.addBossUserRole(bossUserRole);
			}
			s_list.removeAll(t_list); //取差集 获取后台数据库删除的角色
			//后台数据剔除前端用户选择的重复数据，然后删除差集数据
			for(Integer role :s_list){
				for (int l = 0; l < rolelist.size(); l++) {
					if(role.intValue() ==rolelist.get(l).getRoleId()){
						BossUserRole bossUserRole = rolelist.get(l);
						bossUserRole.setStatus(1);   //add by Mr.song 2015.4.28 15:12 ,1表示软删除，0表示有效
						bossUserRole.setUpdateTime(date);
						bossUserDao.alertBossUserRole(bossUserRole);
					}
				}
			}
		}
	}

	@Override
	public List<BossUser> getBossUsersByOrgId(Integer orgId,String status) {
		List<BossUser> list = new ArrayList<BossUser>();
		List<BossUser> userList = null;
		if(status.equals("0")){
			userList = bossUserDao.getBossUsersByOrgId(orgId);
		}else if(status.equals("1")){
			userList = bossUserDao.getCurAndSubBossUsersByOrgId(orgId);
		}
		if(CollectionUtils.isNotEmpty(userList)){
			list.addAll(userList);
		}
		return list;
	}

	@Override
	public List<BossUser> getBossUsersByCondition(@SuppressWarnings("rawtypes") Page page) {
		List<BossUser> list = bossUserDao.getBossUsersByCondition(page);
		for (BossUser bossUser2 : list) {
			String porgName = "";
			if(bossUser2.getOrgId()!=null){
				Organization org = organizationDao.getOrganizationById(Integer.parseInt(bossUser2.getOrgId().toString()));
				if(org!=null){
					String orgName = org.getOrgName();
					while (org.getParentId()!=0) {
						Organization org1 = organizationDao.getOrganizationById(org.getParentId());
						porgName = org1.getOrgName()+"/"+porgName;
						org=org1;
					}
					porgName = porgName+orgName;
				}
			}
			bossUser2.setOrgName(porgName);
		}
		return list;
	}

	@Override
	public BossUser findBossUserByUserCode(String userCode,boolean...ignoreStatus) {
		BossUser bossUser = bossUserDao.findBossUserByUserCode(userCode,ignoreStatus);
		if(bossUser!=null){
			if(bossUser.getOrgId()!=null){
				Organization org = organizationDao.getOrganizationById(Integer.parseInt(bossUser.getOrgId().toString()));
				if(org!=null){
					bossUser.setOrgName(org.getOrgName());
				}
			}
			//取出角色信息 插入bossUser对象中
			List<BossUserRole> rolelist = bossUserOrgRoleDao.selectBossUserRole(String.valueOf(bossUser.getUserId()));
			if (rolelist.size() > 0) {
				StringBuilder roleName = new StringBuilder();
				StringBuilder roleId = new StringBuilder();
				for(int i=0;i<rolelist.size();i++){
					roleName.append(rolelist.get(i).getBossRole().getRoleName()).append(",");
					roleId.append(rolelist.get(i).getBossRole().getRoleId()).append(",");
				}
				bossUser.setRoleName(roleName.substring(0, roleName.lastIndexOf(",")).toString());
				bossUser.setRoleId(roleId.substring(0, roleId.lastIndexOf(",")).toString());
			}
		}
		return bossUser;
	}

	@Override
	public int secretReset(String userId) {
		BossUser bossuser = bossUserDao.findBossUserByUserId(userId);
		String newPassword = GetInitPassword.get6BitPwd();
		Password pass = EncryptUtil.JointownEncode(newPassword);
		bossuser.setPassword(pass.getPassword());
		bossuser.setSalt(pass.getSalt());
		Date date = new Date();
		bossuser.setUpdateTime(date);
		// 短信发送内容
		String messageContent = GetMessageContext.getSecretResetMsg(bossuser.getUserCode(), newPassword);
		// 接收短信的手机号
		String mobile = bossuser.getMobile();

		// 需发送短信
		int modifyFlag = bossUserDao.alterBossUser(bossuser);
		if (modifyFlag > 0) {
			// 发送短信
			//MessageSend.sendMessage(mobile, messageContent);
			//alter by biran 20150908 修改为异步方式发短信
			taskExecutor.execute(messageConfigManage.getMessageChannelTask(mobile,messageContent));
		}
		return 0;
	}
	
	@Override
	public BossUserOrgRoleVO getBossUserOrgRole(String userCode) {
		BossUserOrg bossUserOrg = bossUserOrgRoleDao.selectBossUserOrg(userCode).get(0);
		int userId = bossUserOrg.getUserId();
		
		BossUserOrgRoleVO bossUserOrgRoleVO = new BossUserOrgRoleVO();
		if (null == bossUserOrg.getOrganization()) {
			bossUserOrgRoleVO.setOrgName("");
		}else {
			bossUserOrgRoleVO.setOrgName(bossUserOrg.getOrganization().getOrgName());
		}
		bossUserOrgRoleVO.setUserCode(userCode);
		
		List<BossUserRole> roleList = bossUserOrgRoleDao.selectBossUserRole(String.valueOf(userId));
		if (roleList.size() > 0) {
			StringBuilder sBuilder = new StringBuilder();
			for(int i=0;i<roleList.size();i++){
				sBuilder.append(roleList.get(i).getBossRole().getRoleName()).append(",");
			}
			bossUserOrgRoleVO.setRoleName(sBuilder.substring(0, sBuilder.lastIndexOf(",")).toString());
		}
		return bossUserOrgRoleVO;
	}

	@Override
	public String modifyBossUserSecret(String userCode,String oldPassword,String newPassword) {
		BossUser bossUser = bossUserOrgRoleDao.getBossUserPasswordInfo(userCode);
		String salt = bossUser.getSalt();
		Password oldPwd = EncryptUtil.JointownEncode(oldPassword, salt);
		if (!oldPwd.getPassword().equals(bossUser.getPassword())) {
			return "旧密码错误!";
		}
		
		Password newPwd = EncryptUtil.JointownEncode(newPassword,salt);
		if (newPwd.getPassword().equals(bossUser.getPassword())) {
			return "新旧密码不能一样!";
		}
		
		Password newGeneratePassword =EncryptUtil.JointownEncode(newPassword);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userCode", userCode);
		map.put("password", newGeneratePassword.getPassword());
		map.put("salt", newGeneratePassword.getSalt());
		return String.valueOf(bossUserOrgRoleDao.modifyBossUserSecret(map));
	}

}
