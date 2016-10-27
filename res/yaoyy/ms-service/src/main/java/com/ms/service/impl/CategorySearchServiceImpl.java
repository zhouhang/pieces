package com.ms.service.impl;

import com.github.pagehelper.PageInfo;
import com.ms.dao.elasticsearch.document.CategoryDoc;
import com.ms.dao.elasticsearch.repository.CategorySearchRepository;
import com.ms.dao.model.Category;
import com.ms.dao.vo.CategoryVo;
import com.ms.service.CategorySearchService;
import com.ms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * Created by xiao on 2016/10/27.
 */
@Service
public class CategorySearchServiceImpl implements CategorySearchService {

    @Autowired
    private CategorySearchRepository categorySearchRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Override
    public CategoryDoc save(Category category) {
        CategoryVo  categoryVo= categoryService.getVoById(category.getId());
        CategoryDoc commodityDoc =  vo2doc(categoryVo);
        categorySearchRepository.save(commodityDoc);
        return commodityDoc;
    }

    @Override
    public List<CategoryDoc> findByCategoryName(String categoryName) {
        SearchQuery nameSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("variety",categoryName))
                .withPageable(new PageRequest(0,10)).build();
        Page<CategoryDoc> categoryDocPage = esTemplate.queryForPage(nameSearchQuery,CategoryDoc.class);
        return categoryDocPage.getContent();
    }

    @Override
    public void createAllCategoryDoc() {
        List<CategoryDoc> categoryDocList = new ArrayList<>();
        for(int i=1;;i++){
            PageInfo<CategoryVo> categoryVoPageInfo = categoryService.findVoByPage(50,i);
            for(CategoryVo commodityVO : categoryVoPageInfo.getList()){
                CategoryDoc categoryDoc = vo2doc(commodityVO);
                categoryDocList.add(categoryDoc);
            }
            if(categoryVoPageInfo.getList().size()<50){
                break;
            }
        }
        categorySearchRepository.deleteAll();
        categorySearchRepository.save(categoryDocList);

    }

    @Override
    public void deleteByCategoryId(Integer categoryId) {
        categorySearchRepository.delete(categoryId);
    }


    private CategoryDoc vo2doc(CategoryVo category){
        CategoryDoc categoryDoc = new CategoryDoc();
        categoryDoc.setId(category.getId());
        categoryDoc.setVariety(category.getVariety());
        categoryDoc.setParentName(category.getParentName());
        categoryDoc.setPictureUrl(category.getPictureUrl());
        categoryDoc.setPriceDesc(category.getPriceDesc());
        categoryDoc.setUnit(category.getUnit());
        categoryDoc.setSort(category.getSort());
        categoryDoc.setTitle(category.getTitle());
        categoryDoc.setStatus(category.getStatus());
        return categoryDoc;
    }
}
