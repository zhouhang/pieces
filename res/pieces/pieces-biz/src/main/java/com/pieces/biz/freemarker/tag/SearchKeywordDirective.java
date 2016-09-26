package com.pieces.biz.freemarker.tag;

import com.pieces.dao.vo.AdVo;
import com.pieces.service.AdService;
import com.pieces.service.enums.CodeEnum;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by wangbin on 2016/8/9.
 */
public class SearchKeywordDirective implements TemplateDirectiveModel {

    @Autowired
    private AdService adService;


    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        List<AdVo> adVoList = adService.findByType(CodeEnum.AD_SEARCH.getId());
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<adVoList.size();i++ ){
            AdVo adVo  = adVoList.get(i);

            String aTag =  "<a "+(i==0?"class='hot'":"")+" href='/commodity/search?keyword="+adVo.getTitle()+"'>"+adVo.getTitle()+"</a>";

            sb.append(aTag);
        }
        environment.getOut().append(sb.toString());
    }
}
