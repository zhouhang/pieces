package com.jointown.zy.web.shiro;

import com.jointown.zy.common.model.BossPermission;
import com.jointown.zy.common.shiro.ShiroPermission;

/**
 * boss shiro 权限对象
 * @author LiuPiao
 *
 */
public class BossShiroPermission extends ShiroPermission<BossPermission> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BossShiroPermission(BossPermission permission) {
		super(permission);
	}

	@Override
	public boolean afterPermissionNotEqualsCheck(ShiroPermission<BossPermission> p) {
		String paramResource = p.getPermission().getOperationResource();
		String permissionResource = getPermission().getOperationResource();
		//permission名字存在的情况
		if( (p.getPermission().getPermissionName()!=null) && (!p.getPermission().getPermissionName().equals(getPermission().getPermissionName())) ){
			return false;
		}else{//permission名字不存在的情况,判断 resource
			if(paramResource==null && permissionResource==null){
				return true;
			}
			if((paramResource==null && permissionResource!=null) || (paramResource!=null && permissionResource==null)){
				return false;
			}
			//URL都非空的情况下,再验证是否含有相同URL
			if(paramResource.equals(permissionResource)){
				return true;
			}else{
				//验证是否含有相同前缀URL
				if(paramResource.startsWith(permissionResource)  
						&& paramResource.lastIndexOf("/")>0
						&& !permissionResource.equals("/")){
					return true;		
				}
				//当前权限为按钮权限时,再验证是否含有相同前缀URL
//				if(this.getPermission().getType()!=null && FlagEnum.BOSS_PERMISSION_TYPE_BUTTON.getCode().equals(this.getPermission().getType().toString())){//按钮
//					if(paramResource.startsWith(permissionResource) 
//							&& paramResource.charAt(permissionResource.length())=='/'
//							&& paramResource.lastIndexOf("/")>0
//									&& !permissionResource.equals("/")){
//						return true;
//					}
//				}
				
			}
		}
		return false;
	}

	@Override
	public String getPermissionName() {
		BossPermission permission = getPermission();
		return permission==null?null:permission.getPermissionName();
	}

}
