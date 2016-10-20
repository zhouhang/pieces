package com.ms.boss.shiro;

import com.ms.service.shiro.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer {
	
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());

    }
    
}
