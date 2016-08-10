package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.CommodityDao;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVo;
import com.pieces.tools.utils.FileUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Repository
public class CommodityDaoImpl extends BaseDaoImpl implements CommodityDao {

    public static String param = "pictureUrl";

    @Override
    public Commodity findById(int id) {
        return (Commodity)FileUtil.convertAbsolutePathToUrl(getSqlSession().
                selectOne("com.pieces.dao.CommodityMapper.findById", id), param);
    }

    @Override
    public List<Commodity> findAll() {
        return getSqlSession().selectList("com.pieces.dao.CommodityMapper.findAll");
    }

    @Override
    public PageInfo<Commodity> find(int pageNum, int pageSize) {
        List<Commodity> list = getSqlSession().selectList("com.pieces.dao.CommodityMapper.findAll", null, new RowBounds(pageNum, pageSize));
        list = FileUtil.convertAbsolutePathToUrl(list, param);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public PageInfo<CommodityVo> findByParam(CommodityVo commodity, int pageNum, int pageSize) {
        List<Commodity> list = getSqlSession().selectList("com.pieces.dao.CommodityMapper.findByParam", commodity, new RowBounds(pageNum, pageSize));
        list = FileUtil.convertAbsolutePathToUrl(list, param);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public List<Commodity> findByParamNoPage(CommodityVo commodity) {
        return getSqlSession().selectList("com.pieces.dao.CommodityMapper.findByParam", commodity);
    }

    @Override
    public CommodityVo findVoById(Integer id) {
        CommodityVo commodityVO = (CommodityVo)FileUtil.convertAbsolutePathToUrl(getSqlSession().
                selectOne("com.pieces.dao.CommodityMapper.findVoById", id), param);
        return commodityVO;
    }

    @Override
    public List<CommodityVo> findVoByIds(Collection<Integer> ids) {
        List<CommodityVo> commodityVOList = FileUtil.convertAbsolutePathToUrl(getSqlSession().
                selectList("com.pieces.dao.CommodityMapper.findVoByIds", new ArrayList<>(ids)),param);
        return commodityVOList;
    }


    @Override
    public PageInfo<CommodityVo> findVoByPage(int pageNum, int pageSize) {
        List<Commodity> list = getSqlSession().selectList("com.pieces.dao.CommodityMapper.findVoByPage", null, new RowBounds(pageNum, pageSize));
        list = FileUtil.convertAbsolutePathToUrl(list,param);
        PageInfo page = new PageInfo(list);
        return page;
    }


    @Override
    public int deleteById(int id) {
        return getSqlSession().delete("com.pieces.dao.CommodityMapper.deleteById", id);
    }

    @Override
    public int create(Commodity commodity) {
        return getSqlSession().insert("com.pieces.dao.CommodityMapper.create", commodity);
    }

    @Override
    public int update(Commodity commodity) {
        return getSqlSession().update("com.pieces.dao.CommodityMapper.update", commodity);
    }

    @Override
    public List<CommodityVo> findCommodityByBreedId(Integer id) {
        return FileUtil.convertAbsolutePathToUrl(getSqlSession().
                selectList("com.pieces.dao.CommodityMapper.findCommodityByBreedId", id),param);
    }

    @Override
    public List<CommodityVo> findStandardByBreedId(String ids) {
        CommodityVo vo = new CommodityVo();
        vo.setBreedIds(ids);
        return FileUtil.convertAbsolutePathToUrl(getSqlSession().
                selectList("com.pieces.dao.CommodityMapper.findStandardByBreedId", vo),param);
    }

    @Override
    public List<CommodityVo> findFactoryByBreedId(String ids) {
        CommodityVo vo = new CommodityVo();
        vo.setBreedIds(ids);
        return FileUtil.convertAbsolutePathToUrl(getSqlSession().
                selectList("com.pieces.dao.CommodityMapper.findFactoryByBreedId", vo),param);
    }

    @Override
    public List<CommodityVo> findByIds(String ids) {
        return FileUtil.convertAbsolutePathToUrl(getSqlSession().
                selectList("com.pieces.dao.CommodityMapper.findByIds", ids),param);
    }
}
