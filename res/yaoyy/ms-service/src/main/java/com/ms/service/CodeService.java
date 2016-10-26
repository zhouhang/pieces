package com.ms.service;

import com.github.pagehelper.PageInfo;
import com.ms.dao.model.Code;
import com.ms.dao.vo.CodeVo;

import java.util.List;


public interface CodeService extends ICommonService<Code>{

    public PageInfo<CodeVo> findByParams(CodeVo codeVo,Integer pageNum,Integer pageSize);

    public List<CodeVo> findAllByParams(CodeVo codeVo);
}
