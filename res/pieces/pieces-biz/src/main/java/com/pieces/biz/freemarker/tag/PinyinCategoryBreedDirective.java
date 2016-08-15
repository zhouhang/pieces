package com.pieces.biz.freemarker.tag;

import com.pieces.dao.vo.CategoryVo;
import com.pieces.service.CategoryService;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.redis.RedisManager;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by wangbin on 2016/8/10.
 */
public class PinyinCategoryBreedDirective implements TemplateDirectiveModel {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RedisManager redisManager;


    @Override
    public void execute(Environment environment, Map params, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {

        String body = redisManager.get(RedisEnum.SITE_TAG_PINYIN_CATEGORY.getValue());
        if(StringUtils.isBlank(body)){
            String[]  letterArr = new String[]{"A~E","F~J","K~O","P~T","U~Z"};
            StringBuffer sb = new StringBuffer();

            List<CategoryVo> parentCategoryList = categoryService.findByLevelAndPinyin(1,null,null,30);

            for(CategoryVo parentCategory : parentCategoryList){

                sb.append("<li>");
                sb.append("<div class='cat-name'>").append(parentCategory.getName()).append("</div>");
                sb.append("<div class='cat-list'>");
                for(String letter : letterArr){
                    List<CategoryVo> categoryVoList = categoryService.menuCategoryBreed(parentCategory.getId(),letter);
                    if(categoryVoList.isEmpty()){
                        break;
                    }
                    sb.append("<dl>");
                    sb.append(letterTitle(letter));
                    sb.append("<dd>");
                    for(CategoryVo categoryVo : categoryVoList){
                        sb.append("<a href='commodity/index?breedId=").append(categoryVo.getId()).append("'>").append(categoryVo.getName()).append("</a>").append("\n\r");
                    }
                    sb.append("</dd>");
                    sb.append("</dl>");
                }
                sb.append("<div class='more'>");
                sb.append("<a href='/category/all' class='c-blue'>查看更多 &gt;</a>");
                sb.append("</div>");
                sb.append("</div>");
                sb.append("</li>");
            }
            body = sb.toString();
            redisManager.set(RedisEnum.SITE_TAG_PINYIN_CATEGORY.getValue(),body);
        }
        environment.getOut().append(body);
    }


    private String letterTitle(String letter){
        String[] letterArr = letter.split("~");
        StringBuffer sb = new StringBuffer();
        sb.append("<dt><b>").append(letterArr[0]).append("</b>~<b>").append(letterArr[1]).append("</b> &gt;</dt>");
        return sb.toString();
    }


}
