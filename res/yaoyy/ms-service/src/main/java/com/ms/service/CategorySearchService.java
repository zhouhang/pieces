package com.ms.service;



import com.ms.dao.elasticsearch.document.CategoryDoc;
import com.ms.dao.model.Category;

import java.util.List;

/**
 * Created by xiao on 2016/10/27.
 */
public interface CategorySearchService {
    /**
     * 创建或更新索引
     *
     * @return
     */
    public CategoryDoc save(Category category);


    /**
     * 搜索名称
     *
     * @return
     */
    public List<CategoryDoc> findByCategoryName(String categoryName);

    /**
     * 更新所有索引
     */
    public void createAllCategoryDoc();

    /**
     * 删除所有
     *
     */

    public void  deleteByCategoryId(Integer categoryId);


}
