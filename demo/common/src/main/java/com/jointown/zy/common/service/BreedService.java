package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BreedAttr;
import com.jointown.zy.common.model.BreedCategory;
import com.jointown.zy.common.model.Categorys;
import com.jointown.zy.common.model.Page;

/**
* 项目名称：common   
* 类名称：BreedService   
* 类描述：类目分类Service   
* 创建人：chengchang   
* 创建时间：2014年12月18日 上午10:07   
* 修改人：chengchang   
* 修改时间：2014年12月16日 上午10:07   
* 修改备注：   
* @version 
*
*/
public interface BreedService {
	/**
	 * 
	 * @param breed
	 * @return int 插入成功后返回插入成功的对象的个数 1表示成功，0表示失败
	 * 添加Breed时，如果需要关联到类目表的时候，需要在品种类目关系表中增加一条记录
	 */
	public int addBreed(Breed breed);
	/**
	 * 
	 * @param breed
	 * @param breedCategory
	 * @return
	 * @Description 添加breed后也添加breed和category的关系维护表
	 */
	public int addBreed(Breed breed ,Categorys categorys);
	/**
	 * @author seven
	 * @description 传入breed和categoryId的list,插入到breed和categorys的关系表中
	 * @param breed
	 * @param categoryIds
	 * @return
	 */
	
	public int addBreed(Breed breed ,List<String> categoryIds);
	/**
	 * 插入
	 * @param breed
	 * @param listCategories
	 * @return
	 */
	public int addBreeds(Breed breed,List<Categorys> listCategories);
	/**
	 * 
	 * @param breed
	 * @return int 删除成功后，返回删除的个数
	 * 修改breed中的有效标志，修改categorys中的有效标志
	 */
	public int deleteBreed(Breed breed);
	/**
	 * 根据项目中的需求放进去查询条件，查询出需要的类容
	 * @param map
	 * @return List<Breed>查询出来的类容放在list里面，然后显示在前台上
	 */
	
	public List<Breed> selectBreedBy (Page<Map <String ,Object>> page);
	public Breed selectBreedById(Long id);
	/**
	 * @description 传入breed，breed和Categorys还有breedAttrs数据添加到数据库中
	 * @param breed
	 * @param listCategories
	 * @param breedAttrs
	 * @return
	 */
	public int addBreeds(Breed breed,List<Categorys> listCategories,List<BreedAttr> breedAttrs);
	/**
	 * 传入breedAttr的属性，查询出符合规则的BreedAttr
	 * @param breedAttr
	 * @return
	 */
	public List<BreedAttr> queryBreedAttr(BreedAttr breedAttr);
	/**
	 * @description 通过传递breedId在breedCategory中查询出相关的categorysBreed
	 * @param breedId
	 * @return
	 */
	public List<BreedCategory> queryBreedCategoryByBreedId(Long breedId);
	/**
	 * 
	 * @param breed
	 * @param listCategories
	 * @param breedAttrs
	 * @return
	 */
	public int updateBreeds(Breed breed,List<Categorys> listCategories,List<BreedAttr> breedAttrs);
	
	/**
	 * 将品种及品种关联的类目信息往rabbitmq生产者put消息
	 * @param breed
	 * @param categoryIds 关联的类目ID 逗号隔开
	 */
	public void WmsBreedputMsg(Breed breed, String categoryIds,ApiFlagEnums apiFlag);
	
	/**
	 * 根据品种编码判断是否已存在此编码的品种
	 * @param breedCode 品种编码
	 * @return 
	 */
	public Breed selectBreedByCode(String breedCode);
	
	
	/**
	 * 根据品种名称查询是否存在同名的品种并且忽略状态
	 * @param breedName 品种名称
	 * @return
	 */
	public Breed selectBreedByNameIgnoreStatus(String breedName);
	
	/**
	 * 
	 * @Description: 精确查询品种，根据品种名称
	 * @Author: wangjunhu
	 * @Date: 2015年6月19日
	 * @param breedName
	 * @return
	 */
	Breed findBreedByBreedName(String breedName);
   
	/**
	 * 
	 * @Description: 模糊查询品种，根据名称
	 * @Author: wangjunhu
	 * @Date: 2015年6月19日
	 * @param name
	 * @return
	 */
	List<Breed> findBreedsByName(String name);
	
	
	/**
	    * 
	    * @Description: 模糊匹配品种名  只匹配品种名，不匹配别名
	    * @Author: fanyuna
	    * @Date: 2015年10月13日
	    * @param breedName 
	    * @return
	    */
	   public List<Breed> selectBreedByKeyword(String breedName);
}
