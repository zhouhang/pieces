package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.AdDao;
import com.pieces.dao.model.Ad;
import com.pieces.dao.vo.AdVo;
import com.pieces.tools.utils.FileUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AdDaoImpl extends BaseDaoImpl implements AdDao {

    private String param = "pictureUrl";

    @Override
    public Ad findById(int id) {
        return (Ad) FileUtil.convertAbsolutePathToUrl(getSqlSession().
                selectOne("com.pieces.dao.AdMapper.findById", id), param);
    }


    @Override
    public List<Ad> findAll() {
        return getSqlSession().selectList("com.pieces.dao.AdMapper.findAll");
    }

    @Override
    public PageInfo<Ad> find(int pageNum, int pageSize) {
        List<Ad> list = getSqlSession().selectList("com.pieces.dao.AdMapper.findAll", null, new RowBounds(pageNum, pageSize));
        list = FileUtil.convertAbsolutePathToUrl(list,param);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public int deleteById(int id) {
        return getSqlSession().delete("com.pieces.dao.AdMapper.deleteById", id);
    }

    @Override
    public int create(Ad ad) {
        return getSqlSession().insert("com.pieces.dao.AdMapper.create", ad);
    }

    @Override
    public int update(Ad ad) {
        return getSqlSession().update("com.pieces.dao.AdMapper.update", ad);
    }

    @Override
    public PageInfo<AdVo> findByParam(AdVo adVo, int pageNum, int pageSize) {
        List<Ad> list = getSqlSession().selectList("com.pieces.dao.AdMapper.findByParam", adVo, new RowBounds(pageNum, pageSize));
        list = FileUtil.convertAbsolutePathToUrl(list,param);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public List<AdVo> findByType(Integer typeId) {
        List<AdVo> list = getSqlSession().selectList("com.pieces.dao.AdMapper.findByType", typeId);
        list = FileUtil.convertAbsolutePathToUrl(list,param);
        return list;
    }
}
