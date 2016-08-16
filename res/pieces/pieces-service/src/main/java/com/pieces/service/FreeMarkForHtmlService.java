package com.pieces.service;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Map;

/**
 * Created by Administrator on 14-5-19.
 */
public interface FreeMarkForHtmlService {




    void geneHtmlFile(String templateFileName,
                      String htmlFilePath,
                      String htmlFileName,
                      Map propMap)throws Exception;

    /**
     *
     * @param templatePathName 模板路径
     * @param templateFileName 模板名称
     * @param htmlFilePath     生成静态文件目录
     * @param htmlFileName     生成静态文件名称
     * @param propMap          生成静态文件freemark参数
     * @throws Exception
     */
    void geneHtmlFile(FreeMarkerConfigurer freeMarkerConfigurer,
                      String templatePathName,
                      String templateFileName,
                      String htmlFilePath,
                      String htmlFileName,
                      Map propMap)throws Exception;

}
