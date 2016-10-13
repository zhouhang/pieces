package com.ms.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by wangbin on 2016/6/28.
 */
public interface ICommonService<T> {
    public List<T> findAll();
    public PageInfo<T> find(int pageNum, int pageSize);
    public T findById(int id);
    public int deleteById(int id);
    public int create(T t);
    public int update(T t);
}
