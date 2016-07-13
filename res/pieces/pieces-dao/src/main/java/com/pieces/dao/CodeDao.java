package com.pieces.dao;

import java.util.List;

import com.pieces.dao.model.Code;

public interface CodeDao extends ICommonDao<Code>{

	public List<Code> find(Code code);
}
