package com.jointown.zy.common.shiro;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;

/**
 * Shiro 公共permission类，具体验证逻辑交由属性中保存的各子系统的 业务permission对象实现
 * @author LiuPiao
 *
 * @param <T>
 */
public abstract class ShiroPermission<T> implements Permission, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T permission;
	
	public ShiroPermission(T permission) {
		this.permission = permission;
	}
	
	/**
	 * 实现比较两个permission对象的逻辑
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean implies(Permission p) {
		if(p==null || this==p)
			return true;
		if(p instanceof ShiroPermission){
			T parmPermission = (T)((ShiroPermission) p).getPermission();
			//剔除当前permission为null的情况
			if(getPermission()==null){
				if(parmPermission==null){
					return true;
				}
				return false;
			}
			//剔除参数permission为null的情况,每个permission子类覆盖equals逻辑
			if(parmPermission==null || getPermission().equals(parmPermission)){
				return true;
			}
			//判断permissionName的情况,必须是完整的permission对象,名称非空
			if(getPermissionName()!=null){
				//交由子类判断属性permission匹配不成功后的逻辑
				return afterPermissionNotEqualsCheck((ShiroPermission)p);
			}
		}
		//按钮的情况，根据按钮的内容进行判断
		if(p instanceof WildcardPermission){
			if(this.getPermissionName()!=null&&!this.getPermissionName().equals("")){
				WildcardPermissionResolver r=new WildcardPermissionResolver();
				Permission p1=r.resolvePermission(this.getPermissionName());
				if(p1.implies(p)){
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * 具体实现当两个permission对象不匹配的时候,自定义的匹配逻辑，交由每个子类实现
	 * @param p
	 * @return
	 */
	public abstract boolean afterPermissionNotEqualsCheck(ShiroPermission<T> p);
	
	/**
	 * 具体子类实现获取permission名
	 * @return
	 */
	public abstract String getPermissionName();

	public T getPermission() {
		return permission;
	}

	public ShiroPermission<T> setPermission(T permission) {
		this.permission = permission;
		return this;
	}


}
