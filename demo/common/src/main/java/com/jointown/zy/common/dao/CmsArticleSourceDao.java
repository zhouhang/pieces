package com.jointown.zy.common.dao;

import java.util.HashMap;
import java.util.List;

import com.jointown.zy.common.model.CmsArticleSource;
import com.jointown.zy.common.vo.CmsArticleSourceVo;

/**
*   
* 项目名称：common  
* 类名称：CmsArticleSourceDao  
* 类描述：  文章来源表增删改查
* 创建人：Mr.songwei  
* 创建时间：2014-12-5 上午11:21:04  
* 修改人：  
* 修改时间：2014-12-5 上午11:21:04  
* 修改备注：  
* @version   
*
 */
public interface CmsArticleSourceDao {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-11-26 09:46:32
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-11-26 09:46:32
     */
    int insert(CmsArticleSource record);

    /**
     * 插入，空属性不会插入，建议用它
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-11-26 09:46:32
     */
    int insertSelective(CmsArticleSource record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-26 09:46:32
     */
    CmsArticleSource selectByPrimaryKey(Long id);
    
    /**
     * 查询所有的文章，以及对应的栏目名称
     * 参数:HashMap<String,String> 包括查询日期，文章标题，关键字
     * 返回:List<CmsArticleSourceVo>
     * @ibatorgenerated 2014-11-26 09:46:32
     */
    public List<CmsArticleSourceVo> selectAll(HashMap<String,String> queryString);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-26 09:46:32
     */
    int updateByPrimaryKeySelective(CmsArticleSource record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-26 09:46:32
     */
    int updateByPrimaryKey(CmsArticleSource record);
}