package com.pieces.dao;

import java.util.List;

import com.pieces.dao.annotation.AutoMapper;
import com.pieces.dao.model.Code;

@AutoMapper
public interface CodeDao extends ICommonDao<Code>{

	 List<Code> find(Code code);
	 List<Code> findByString(String str);

}
