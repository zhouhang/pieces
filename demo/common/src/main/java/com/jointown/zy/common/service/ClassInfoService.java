package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.Categorys;
import com.jointown.zy.common.model.ClassInfo;

/**
 * 
*    
* 项目名称：common   
* 类名称：ClassInfoService   
* 类描述：类目分类Service   
* 创建人：fanyuna   
* 创建时间：2014年12月16日 上午10:13:38   
* 修改人：fanyuna   
* 修改时间：2014年12月16日 上午10:13:38   
* 修改备注：   
* @version    
*
 */
public interface ClassInfoService {

	/**
	 * 
	* @Title: addClassInfo 
	* @Description: 添加分类 先判断是否存在此名称的分类，如果存在则不添加
	* @param className 分类名
	* @param userId 添加人ID
	* @return int  返回新增分类的主键，如果添加不成功则返回null
	
	* @throws
	 */
	public Long addClassInfo(String className,String operateStrage,Long userId);
	
	/**
	 * 
	* @Title: updateClassInfo 
	
	* @Description: 修改分类 先判断是否存在此名称的分类，如果存在则不修改
	* @param updateClassName 修改的分类名
	* @param classId 分类的ID
	* @param userId 修改人ID
	* @return int   是否修改成功 1成功 0 失败
	* @throws
	 */
	public int updateClassInfo(String updateClassName,Long classId,Long userId,String classStrage);
	
	/**
	 * 
	* @Title: deleteClassInfo 
	* @Description: 删除分类（逻辑删除） 删除时需要判断此分类下是否有有效类目，有的话 则不能删除
	* @param  classId 分类的ID
	* @param  userId 删除人ID
	* @return int   是否删除成功 1成功 0 失败
	* @throws
	 */
	public int deleteClassInfo(Long classId,Long userId);
	
	/**
	 * 
	
	* @Title: getEffectClass 
	
	* @Description: 获取有效的类目分类
	
	* @return List<ClassInfo>   有效的类目分类列表
	
	* @throws
	 */
	public List<ClassInfo> getEffectClass();
	
	/**
	* @Title: updateOperateStrate 
	
	* @Description: 更新类目分类的运营策略
	* @param @param classId 类目分类ID
	* @param @param strategy 运营策略
	* @param @param userId 更新人ID
	* @return int    是否更新成功 1成功 0 失败
	
	* @throws
	 */
	public int  updateOperateStrate(Long classId,String strategy,Long userId); 
	
	/**
	 * 
	
	* @Title: getClassByName 
	
	* @Description: 根据分类名查看是否存在此分类信息 由于不能添加
	
	* @param @param className 分类名
	
	* @return boolean    List<ClassInfo> 
	
	* @throws
	 */
	public List<ClassInfo> getClassByName(String className,Long classId);
	
	/**
	
	* @Title: existsCategoryByClass 
	
	* @Description: 根据分类ID查询是否存在有效类目
	
	* @param @param classId
	* @param @return    设定文件 
	
	* @return List<Categorys>    返回类型 
	
	* @throws
	 */
	public List<Categorys> getCategoryByClass(Long classId);
	
	public ClassInfo selectByPrimaryKey(Long classId);
	
}
