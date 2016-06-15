package com.jointown.zy.web.shiro;

import com.jointown.zy.common.model.BossPermission;
import com.jointown.zy.common.shiro.ShiroPermission;

/**
 * boss shiro 权限对象
 * 
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
	public boolean afterPermissionNotEqualsCheck(
			ShiroPermission<BossPermission> p) {
		String paramResource = p.getPermission().getOperationResource();
		String permissionResource = getPermission().getOperationResource();
		if ((p.getPermission().getPermissionName() != null)
				&& (!p.getPermission().getPermissionName()
						.equals(getPermission().getPermissionName()))) {
			return false;
		}
		// 判断 resource
		if (paramResource == null && permissionResource == null) {
			return true;
		}
		if ((paramResource == null && permissionResource != null)
				|| (paramResource != null && permissionResource == null)) {
			return false;
		}
		// bossPermission对象匹配后,URL都非空的情况下,再验证是否含有相同前缀URL，有则表示拥有同一权限
		if (paramResource != null) {
			if (paramResource.equals(permissionResource)
					|| (paramResource.startsWith(permissionResource)
							&& paramResource.lastIndexOf("/") > 0 && !permissionResource
								.equals("/"))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getPermissionName() {
		BossPermission permission = getPermission();
		return permission == null ? null : permission.getPermissionName();
	}

}
