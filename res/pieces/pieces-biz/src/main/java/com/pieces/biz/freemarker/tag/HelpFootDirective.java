package com.pieces.biz.freemarker.tag;

import com.pieces.service.ArticleService;
import com.pieces.service.enums.ModelEnum;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

/**
 * Created by feng on 2016/8/10.
 */
public class HelpFootDirective implements TemplateDirectiveModel {

    @Autowired
    ArticleService articleService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        environment.setVariable("articleCategorylist", DEFAULT_WRAPPER.wrap(articleService.getCategoryListByModelId(ModelEnum.help.getValue())));
        templateDirectiveBody.render(environment.getOut());
    }
}
