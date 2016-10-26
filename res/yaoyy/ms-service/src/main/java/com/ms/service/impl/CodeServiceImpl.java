package com.ms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.dao.ICommonDao;
import com.ms.dao.CodeDao;
import com.ms.dao.model.Code;
import com.ms.dao.vo.CodeVo;
import com.ms.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CodeServiceImpl  extends AbsCommonService<Code> implements CodeService{

	@Autowired
	private CodeDao codeDao;


	@Override
	public PageInfo<CodeVo> findByParams(CodeVo codeVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<CodeVo>  list = codeDao.findByParams(codeVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public List<CodeVo> findAllByParams(CodeVo codeVo) {
		return codeDao.findByParams(codeVo);
	}


	@Override
	public ICommonDao<Code> getDao() {
		return codeDao;
	}

}
