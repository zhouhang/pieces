package com.jointown.zy.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BreedCategory;
import com.jointown.zy.common.model.Categorys;
import com.jointown.zy.common.model.ClassInfo;
import com.jointown.zy.common.model.Page;

/**
 * 
*    
* 项目名称：common   
* 类名称：CategorysService   
* 类描述：目录Service   
* 创建人：fanyuna   
* 创建时间：2014年12月16日 下午3:51:44   
* 修改人：fanyuna   
* 修改时间：2014年12月16日 下午3:51:44   
* 修改备注：   
* @version    
*
 */
public interface CategorysService {
	
	/**
	 * 功能：根据主键id修改排序规则
	 * 增加日期：2015.4.3
	 * 添加人：Mr.song
	 * 参数列表：id 表示类目-品种关系表主键， cindex 表示自定义排序数字（只能是正整数）
	 * 返回状态:修改成功返回1,修改失败返回0
	 */
	public boolean updateIndex(String[] array);
	
	/**
	 * 
	* @Title: addCategory 
	* @Description: 新增类目  同一分类下类目名称不允许重复，存在则添加不成功
	* 1.查看父级类目的IS_LEAF是否为是即叶子结点，如果是则需要修改
	* 2.类目顺序：查看其父节点的子节点数，并将个数+1
	* 3.层级：将父节点层级+1
	* 4.路径：将父级路径+"/主键"
	* @param @param category 类目对象  包含：分类、名称、添加人、父级类目ID等
	* @return int    是否添加成功 1成功 0 失败
	
	* @throws
	 */
	public int addCategory(Categorys category);
	
    /**
     * 总体功能描述：后台类目自定义排序验证规则
     * 1.根据传入的类目id,自定义排序序号，来查找类目下是否有重复的排序，如果有则返回对应的主键，便于前台其他业务逻辑开展;
     * 2.自定义规则“0”是特殊情况，新添加的类目品种的规则都是默认0，如果出现0则通过验证；
     */
	public int getExistRules(String cindex,String categorysid);
	
	/**
	 * 
	
	* @Title: getCategoryByClassAndName 
	
	* @Description: 获取指定分类下，指定名字的有效类目
	
	* @param @param classId 分类ID
	* @param @param name  类目名称
	* @param @return    设定文件 
	
	* @return List<Categorys>    返回类型 
	
	* @throws
	 */
	public List<Categorys> getCategoryByClassAndName(Long classId,String name,Long categoryId);
	
	/**
	 * 
	
	* @Title: updateCategoryName 
	
	* @Description: 修改类目名称 同一分类下类目名称不允许重复，存在则修改不成功
	
	* @param @param categoryId 类目ID
	* @param @param categoryName 类目名称
	
	* @return int    是否修改成功 1成功 0 失败
	
	* @throws
	 */
	public int updateCategoryName(Long categoryId,String categoryName,Long userId);
	
	/**
	 * 
	
	* @Title: deleteCategory 
	
	* @Description: 删除目录 （逻辑删除）如果当前目录有子节点或当前目录下挂品种了 则不能删除
					删除后需查看其父节点是否还有子目录，如果没有 将其父节点的IS_LEAF更改为1 即叶子节点
	* @param @param categoryId
	* @param @param userId
	* @param @return    设定文件 
	
	* @return int    返回类型 
	
	* @throws
	 */
	public int deleteCategory(Long categoryId,Long userId,Long classId);
	
	/**
	 * 
	
	* @Title: updateCategoryOrder 
	
	* @Description: 修改类目顺序 
	* 当前类目的顺序为原始顺序，移动到的顺序为目标顺序 只针对同一级类目即其父节点与当前操作类目的父节点相同的类目
    * 如果原始顺序<目标顺序，则大于原始顺序且小于等于目标顺序的类目顺序全减1；
    * 如果原始顺序>目标顺序，则小于原始顺序且大于等于目标顺序的类目顺序全加1
	* @param @param categoryId 类目ID
	* @param @param userId  修改人
	* @param @param targetId  目标顺序对应的类目ID
	
	* @return int    是否修改成功 1成功 0 失败
	
	* @throws
	 */
	public int updateCategoryOrder(Long categoryId,Long userId,Long targetId);
	
	/**
	 * 
	
	* @Title: selectAllCategory 
	
	* @Description: 根据分类ID获取其所有的有效的类目
	
	* @return List<Categorys>    类目集合
	
	* @throws
	 */
	public List<Categorys> selectCategoryBy(Long classId);
	
	
	/**
	 * 
	
	* @Title: getBreedByCategory 
	
	* @Description: 根据类目ID和查询条件（品种名称/别名、品种编码、产地）查询品种 及上级路径
	* 如果设置类目ID，则查询当前类目及其所有子类目关联的品种
	* @param @param map 查询条件（类目ID、品种名称/别名、品种编码、产地）
	
	* @return List<Map<String,Object>>    品种列表
	
	* @throws
	 */
	public List<Map<String,Object>> getBreedByCategory(Page<Map<String,Object>> page);
	
	/**
	 * 
	
	* @Title: getChildNode 
	
	* @Description: 获取指定目录的子类目 
	
	* @param @param categoryId 类目ID
	* @param @return    设定文件 
	
	* @return List<Categorys>    子类目集合
	
	* @throws
	 */
	public List<Categorys> getChildNode(Long categoryId,Long classId);
	/**
	 * @Title getAllTree
	 * @Description 更具每个分类去class_info 和categorys中分离出class_info 中的tree
	 */
	public List<Object> getAllTree();
	
	/**
	 * 
	
	* @Title: associateCategoryAndBreed 
	
	* @Description: 在指定目录下挂品种
	
	* @param @param categoryId	目录ID
	* @param @param breedArray  品种数组
	
	* @return void    返回类型 
	
	* @throws
	 */
	public void associateCategoryAndBreed(Long categoryId,String[] breedArray);
	
	/**
	 * 
	
	* @Title: deleteCategoryAndBreedRela 
	
	* @Description: 删除目录与品种的关系
	
	* @param @param breedCategoryId 类目ID
	* @return int    是否删除成功 1成功 0 失败
	
	* @throws
	 */
	public int deleteCategoryAndBreedRela(Long breedCategoryId);
	
	/**
	 * 
	
	* @Title: selectBreedByCat 
	
	* @Description: 查询当前类目的品种 
	
	* @param @param categoryId
	* @param @return    设定文件 
	
	* @return List<Breed>    返回类型 
	
	* @throws
	 */
	public List<Breed> selectBreedByCat(Long categoryId);
	
	/**
	 * 
	
	* @Title: getCategorysById 
	
	* @Description: 根据类目ID获取类目信息
	
	* @param @param categoryId
	* @param @return    设定文件 
	
	* @return Categorys    返回类型 
	
	* @throws
	 */
	public Categorys getCategorysById(Long categoryId);
	
	/**
	 * 
	
	* @Title: getNotAssociateBreedBy 
	
	* @Description: 查询指定类目没挂的品种， 按添加时间降序，品种名称，编码，别名，产地，查询条件模糊查询
	
	* @param @param page
	* @param @return    设定文件 
	
	* @return List<Breed>    返回类型 
	
	* @throws
	 */
	public List<Breed> getNotAssociateBreedBy(Page page);
	/**
	 * @author seven 
	 * @description查询出整个树，并且勾选出选中的复选框
	 * @param breed
	 * @param chkDisabled true表示复选框不可以，false表示复选框可用
	 * @return
	 */
	public List<Object> getAllTreeAndChecked(Breed breed,boolean chkDisabled);
	/**
	 * @description 获取所有的树，并且获取标记选择的部分
	 * @param listCategorys
	 * @param classInfo
	 * @param listBreedCategory
	 * @param boolean chkDisabled 该复选框是否禁用 true表示禁用，false表示可以使用
	 * @return
	 */
	public Map getCategorysTreeChecked
	(List<Categorys> listCategorys ,ClassInfo classInfo,List <BreedCategory> listBreedCategory ,boolean chkDisabled);
	/**
	 * 通过categoryId查询出category
	 * @param id
	 * @return
	 */
	public Categorys selectByPrimaryKey(Long id) ;
	/**
	 * 根据类目名获取“形态分类”下的类目信息
	 * @param name 类目名
	 * @return
	 */
	public List<Categorys> getCategoryByName(Long breedId);

	
}
