package com.ms.dao;

import com.ms.dao.config.AutoMapper;
import com.ms.dao.model.Code;
import com.ms.dao.vo.CodeVo;

import java.util.List;
@AutoMapper
public interface CodeDao extends ICommonDao<Code>{

    public List<CodeVo> findByParams(CodeVo codeVo);

}
