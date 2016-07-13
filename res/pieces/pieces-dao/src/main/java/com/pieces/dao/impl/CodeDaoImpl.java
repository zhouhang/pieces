package com.pieces.dao.impl;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.CodeDao;
import com.pieces.dao.model.Code;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class CodeDaoImpl extends BaseDaoImpl implements CodeDao{

        @Override
        public Code findById(int id) {
            return getSqlSession().selectOne("com.pieces.dao.CodeMapper.findById", id);
        }


        @Override
        public List<Code> findAll() {
            return getSqlSession().selectList("com.pieces.dao.CodeMapper.findAll");
        }

        @Override
        public PageInfo<Code> find(int pageNum, int pageSize) {
            List<Code> list = getSqlSession().selectList("com.pieces.dao.CodeMapper.findAll", null,new RowBounds(pageNum, pageSize));
            PageInfo page = new PageInfo(list);
            return page;
        }

        @Override
        public int deleteById(int id) {
            return getSqlSession().delete("com.pieces.dao.CodeMapper.deleteById",id);
        }

        @Override
        public int create(Code code) {
            return getSqlSession().insert("com.pieces.dao.CodeMapper.create",code);
        }

        @Override
        public int update(Code code) {
            return getSqlSession().update("com.pieces.dao.CodeMapper.update",code);
        }


		@Override
		public List<Code> find(Code code) {
			return getSqlSession().selectList("com.pieces.dao.CodeMapper.find",code);
		}
		
		/**
		 * upate code
		 * @param newString 新的code字符串数组,如规格，产地
		 * @param relatedCode 关联的品种
		 * @param typeId code类型
		 */
		public void updateCode(String[] newString,Integer relatedCode,Integer typeId){
			//查询原始specifications
			Code code = new Code();
			code.setRelatedCode(relatedCode);
			code.setTypeId(typeId);
			List<Code> specifications_old = this.find(code);
			//将两个新，旧两个转成map，便于比较
			Map<String,Code> old_set = new HashMap<String,Code>();
			Map<String,String> new_set = new HashMap<String,String>();
			Map<String,String> common_set = new HashMap<String,String>();
			for(Code code_old : specifications_old){
				old_set.put(code_old.getName(), code_old);
			}
			for(int i=0 ; i<newString.length ; i++){
				new_set.put(newString[i].trim(),newString[i].trim());
			}
			//求交集和需要删除的
			for(String key : old_set.keySet()){
				Code delet = old_set.get(key);
				if(new_set.containsKey(key)){
					common_set.put(key, key);
					if(delet.getStatus() != 1){
						delet.setStatus(1);
						this.update(delet);
					}
				}else{
					
					delet.setStatus(-1);
					this.update(delet);
				}
			}
			//新添加的的
			for(String key : new_set.keySet()){
				if(!common_set.containsKey(key)){
					int i = old_set.size();
					Code spe = getCode(i, key, relatedCode, typeId);
					this.create(spe);
				}
			}
		}
		
		/**
		 * 根据条件构造code
		 * @param code
		 * @param name
		 * @param relatedCode
		 * @param typeId
		 * @return
		 */
		public Code getCode(int code,String name,Integer relatedCode,Integer typeId){
			Code spe = new Code();
			spe.setCode(String.valueOf(code));
			spe.setName(name);
			spe.setRelatedCode(relatedCode);
			spe.setStatus(1);
			spe.setTypeId(typeId);
			spe.setCreateTime(new Date());
			return spe;
		}
		
}
