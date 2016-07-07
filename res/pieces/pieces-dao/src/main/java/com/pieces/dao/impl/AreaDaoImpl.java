package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.AreaDao;
import com.pieces.dao.model.Area;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangbin on 2016/6/27.
 */
@Repository
public class AreaDaoImpl extends BaseDaoImpl implements AreaDao{

    @Override
    public List<Area> findByParent(Integer parentId) {
        return getSqlSession().selectList("com.pieces.dao.AreaMapper.selectByParent", parentId,new RowBounds(1, 10));
    }

    @Override
    public List<Area> findByLevel(Integer level) {
        return getSqlSession().selectList("com.pieces.dao.AreaMapper.selectByLevel", level);
    }

    @Override
    public PageInfo<Area> findByPage(Integer level, Integer pageNum, Integer pageSize) {
        List<Area> list =getSqlSession().selectList("com.pieces.dao.AreaMapper.selectByLevel", level,new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    /**
     * 通过地区ID查询该地区全部信息
     * @param id
     * @return
     */
    @Override
    public Area findParentsByCode(Integer id) {
        return getSqlSession().selectOne("com.pieces.dao.AreaMapper.selectParentsById", id);
    }

    @Override
    public Area findById(int id) {
        return getSqlSession().selectOne("com.pieces.dao.AreaMapper.findById", id);
    }


    @Override
    public List<Area> findAll() {
        return getSqlSession().selectList("com.pieces.dao.AreaMapper.findAll");
    }

    @Override
    public PageInfo<Area> find(int pageNum, int pageSize) {
        List<Area> list = getSqlSession().selectList("com.pieces.dao.AreaMapper.findAll", null,new RowBounds(pageNum, pageSize));
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public int deleteById(int id) {
        return getSqlSession().delete("com.pieces.dao.AreaMapper.deleteById",id);
    }

    @Override
    public int create(Area area) {
        return getSqlSession().insert("com.pieces.dao.AreaMapper.create",area);
    }

    @Override
    public int update(Area area) {
        return getSqlSession().update("com.pieces.dao.AreaMapper.update",area);
    }

	@Override
	public Area findByCode(String areacode) {
		return getSqlSession().selectOne("com.pieces.dao.AreaMapper.selectByCode",areacode);
	}

}
