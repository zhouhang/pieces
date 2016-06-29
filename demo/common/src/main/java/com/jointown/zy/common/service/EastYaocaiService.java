package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.EastYaocai;

public interface EastYaocaiService {

	/**
     * 查询药材品种，根据品种档案
     * @return
     */
    List<EastYaocai> findEastYaocaiPzs();
}
