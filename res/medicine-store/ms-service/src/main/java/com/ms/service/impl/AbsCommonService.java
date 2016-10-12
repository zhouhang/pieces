package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.service.ICommonService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangbin on 2016/6/28.
 */
@Transactional(readOnly = true)
public abstract class AbsCommonService<T> implements ICommonService<T> {

    public abstract ICommonDao<T> getDao();

    public List<T> findAll(){
       return getDao().findAll();
    }
    public PageInfo<T> find(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = getDao().findAll();
        PageInfo page = new PageInfo(list);
        return page;
    }
    public T findById(int id){
        return getDao().findById(id);
    }
    @Transactional
    public int deleteById(int id){
        return getDao().deleteById(id);
    }
    @Transactional
    public int create(T t){
        return getDao().create(t);
    }
    @Transactional
    public int update(T t){
        return getDao().update(t);
    }
}
