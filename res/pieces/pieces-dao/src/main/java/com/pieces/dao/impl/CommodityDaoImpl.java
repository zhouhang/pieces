package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.CommodityDao;
import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;


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
    public PageInfo<CommodityVO> findByParam(CommodityVO commodity, int pageNum, int pageSize) {
        List<Commodity> list = getSqlSession().selectList("com.pieces.dao.CommodityMapper.findByParam", commodity, new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }
    
    @Override
    public List<Commodity> findByParamNoPage(CommodityVO commodity) {
        return getSqlSession().selectList("com.pieces.dao.CommodityMapper.findByParam", commodity);
    }

    @Override
    public CommodityVO findVoById(Integer id) {
        CommodityVO commodityVO =  getSqlSession().selectOne("com.pieces.dao.CommodityMapper.findVoById",id);
        return commodityVO;
    }

    @Override
    public PageInfo<CommodityVO> findVoByPage(int pageNum, int pageSize) {
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
        public List<CommodityVO> findCommodityByBreedId(Integer id) {
            return getSqlSession().selectList("com.pieces.dao.CommodityMapper.findCommodityByBreedId",id);
        }
        
        @Override
        public List<CommodityVO> findStandardByBreedId(String ids) {
        	CommodityVO vo = new CommodityVO();
        	vo.setBreedIds(ids);
            return getSqlSession().selectList("com.pieces.dao.CommodityMapper.findStandardByBreedId",vo);
        }

		@Override
		public List<CommodityVO> findFactoryByBreedId(String ids) {
			CommodityVO vo = new CommodityVO();
        	vo.setBreedIds(ids);
			return getSqlSession().selectList("com.pieces.dao.CommodityMapper.findFactoryByBreedId",vo);
		}
}
