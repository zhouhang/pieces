package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.CommodityDao;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Repository
public class CommodityDaoImpl extends BaseDaoImpl implements CommodityDao {

    @Override
    public Commodity findById(int id) {
        return getSqlSession().selectOne("com.pieces.dao.CommodityMapper.findById", id);
    }

    @Override
    public List<Commodity> findAll() {
        return getSqlSession().selectList("com.pieces.dao.CommodityMapper.findAll");
    }

    @Override
    public PageInfo<Commodity> find(int pageNum, int pageSize) {
        List<Commodity> list = getSqlSession().selectList("com.pieces.dao.CommodityMapper.findAll", null, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public PageInfo<CommodityVo> findByParam(CommodityVo commodity, int pageNum, int pageSize) {
        List<Commodity> list = getSqlSession().selectList("com.pieces.dao.CommodityMapper.findByParam", commodity, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }
    
    @Override
    public List<Commodity> findByParamNoPage(CommodityVo commodity) {
        return getSqlSession().selectList("com.pieces.dao.CommodityMapper.findByParam", commodity);
    }

    @Override
    public CommodityVo findVoById(Integer id) {
        CommodityVo commodityVO =  getSqlSession().selectOne("com.pieces.dao.CommodityMapper.findVoById",id);
        return commodityVO;
    }

    @Override
    public List<CommodityVo> findVoByIds(Set<Integer> ids) {
        List<CommodityVo> commodityVOList =  getSqlSession().selectList("com.pieces.dao.CommodityMapper.findVoByIds",new ArrayList<>(ids));
        return commodityVOList;
    }


    @Override
    public PageInfo<CommodityVo> findVoByPage(int pageNum, int pageSize) {
        List<Commodity> list = getSqlSession().selectList("com.pieces.dao.CommodityMapper.findVoByPage", null, new RowBounds(pageNum, pageSize));
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
            return getSqlSession().selectList("com.pieces.dao.CommodityMapper.findCommodityByBreedId",id);
        }
        
        @Override
        public List<CommodityVo> findStandardByBreedId(String ids) {
        	CommodityVo vo = new CommodityVo();
        	vo.setBreedIds(ids);
            return getSqlSession().selectList("com.pieces.dao.CommodityMapper.findStandardByBreedId",vo);
        }

		@Override
		public List<CommodityVo> findFactoryByBreedId(String ids) {
			CommodityVo vo = new CommodityVo();
        	vo.setBreedIds(ids);
			return getSqlSession().selectList("com.pieces.dao.CommodityMapper.findFactoryByBreedId",vo);
		}

    @Override
    public List<CommodityVo> findByIds(String ids) {
        return getSqlSession().selectList("com.pieces.dao.CommodityMapper.findByIds",ids);
    }
}
