package com.pieces.dao;

import java.util.List;

import com.pieces.dao.model.Code;

public interface CodeDao extends ICommonDao<Code>{

	public List<Code> find(Code code);
	public List<Code> findByString(String str);
	
	//public void updateCode(String[] newString,Integer relatedCode,Integer typeId);
	
	//public Code getCode(int code,String name,Integer relatedCode,Integer typeId);

}
