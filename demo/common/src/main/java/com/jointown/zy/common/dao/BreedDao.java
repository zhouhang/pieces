package com.jointown.zy.common.dao;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.Page;

public interface BreedDao {
	
	 /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BREED
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long breedId);

    /**
     * 
     * @Title :insert
     * @Description :用来插入Breed，其中不包括品种类目关系
     * @mbggenerated
     */
    int insert(Breed record);

    
    /**
     * @Title insert
     * @param record
     * @param categorys
     * @return int 插入成功返回1，失败返回0
     * @Description 插入品种的同时也插入类目，通过品种类目的关系表来维护品种和类目表的联系
     */
    /*int insert(Breed record,Categorys categorys);*/
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BREED
     *
     * @mbggenerated
     */
    int insertSelective(Breed record);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BREED
     *
     * @mbggenerated
     */
    Breed selectByPrimaryKey(Long breedId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BREED
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Breed record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BREED
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Breed record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BREED
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Breed record);
    
    /**
     * 
    
    * @Title: selectBreedBy 
    
    * @Description: 查询当前类目及其所有子类目下的品种：要求map中设置类目ID
    *                或根据条件查询所有品种 
    
    * @param @param map 
    * @param @return    设定文件 
    
    * @return List<Breed>    返回类型 
    
    * @throws
     */
    List<Breed> selectBreedBy(Page<Map<String, Object>> page);
    /**
     * @author seven
     * @description 逻辑删除Breed ，修改修改状态
     * @return
     */
    public int deleteBreedLogic(Breed breed);
    
    
    /**
     * 
    
    * @Title: selectBreedByCategory 
    
    * @Description: 查询指定类目没挂的品种， 按添加时间降序，品种名称，编码，别名，产地，查询条件模糊查询 
    
    * @param @param page 
    * @param @return    设定文件 
    
    * @return List<Breed>    返回类型 
    
    * @throws
     */
    List<Breed> selectBreedByCategory(Page page);
    
    /**
     * 
    
    * @Title: selectBreedInfoBy 
    
    * @Description: 查询当前类目及其所有子类目下的品种：要求map中设置类目ID
    *                或根据条件查询所有品种 及类目路径
    * @param @param page
    * @param @return    设定文件 
    
    * @return List<Map<String,Object>>    返回类型 
    
    * @throws
     */
   List<Map<String,Object>> selectBreedInfoBy(Page<Map<String,Object>> page); 
   
   /**
    * 
   
   * @Title: selectBreedByCat 
   
   * @Description: 查询当前类目的品种 
   
   * @param @param categoryId 类目ID
   * @param @return    设定文件 
   
   * @return List<Breed>    返回类型 
   
   * @throws
    */
   List<Breed> selectBreedByCat(Long categoryId);
   
   List<Breed> selectList(Breed breed);
   
   /**
    * 
    * @Description: 精确查询品种，根据品种名称
    * @Author: wangjunhu
    * @Date: 2015年6月19日
    * @param breedName
    * @return
    */
   Breed selectBreedByBreedName(String breedName);
   
   /**
    * 
    * @Description: 模糊查询品种，根据名称
    * @Author: wangjunhu
    * @Date: 2015年6月19日
    * @param name
    * @return
    */
   List<Breed> selectBreedsByName(String name);
   
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
