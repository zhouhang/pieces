package com.jointown.zy.common.service;

import com.jointown.zy.common.model.EastPzDangan;

public interface EastPzDanganService {

	/**
	 * 查询药材百科档案，根据药材名称
	 * @param ycnam
	 * @return
	 */
	EastPzDangan findByPrimaryKey(String ycnam);
	
    /**
     * 查询品种名称,根据名称
     * @param name
     * @return 有结果：品种名称；无结果：""
     */
    String findBreedNameByName(String name);
}
