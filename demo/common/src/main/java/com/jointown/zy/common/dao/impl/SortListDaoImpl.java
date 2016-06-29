package com.jointown.zy.common.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.SortListDao;
@Repository
public class SortListDaoImpl extends BaseDaoImpl implements SortListDao {
	private static final Logger log = LoggerFactory.getLogger(SortListDaoImpl.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map<Object, Object>> selectAllMedicinal() {
		/*****************start*****************************/
		long l1 = System.nanoTime();
		//1.获取操作的SqlSessionTemplate
		SqlSessionTemplate sqlSession= this.getSqlSession();
		//2.算出所有类目品种
		List <Map<Object, Object>> clist=sqlSession.selectList("com.jointown.zy.common.dao.SortListDao.selectBreedByCategorysFN");
		//热门品种
		List <Map<Object, Object>> rlist=sqlSession.selectList("com.jointown.zy.common.dao.SortListDao.selectBreedByCategoryId");
		//类目集合
		List <Map<Object, Object>> breeds= new ArrayList<Map<Object, Object>>();
		String cid="";
		//3.设置赶回的list
		List resultList = new ArrayList();
		//算出字母ascii码
		int A=(int) "A".toCharArray()[0];
		int F=(int) "F".toCharArray()[0];
		int G=(int) "G".toCharArray()[0];
		int L=(int) "L".toCharArray()[0];
		int M=(int) "M".toCharArray()[0];
		int R=(int) "R".toCharArray()[0];
		int S=(int) "S".toCharArray()[0];
		int Z=(int) "Z".toCharArray()[0];
		for(int i=0;i<clist.size();i++){
			HashMap map = (HashMap)clist.get(i);
			cid= map.get("ID").toString();
			if(i==0){
				HashMap o = new HashMap();
				o.put("categorys_id", clist.get((i)).get("ID"));
				o.put("categorys_name", clist.get((i)).get("CATEGORYS_NAME"));
				breeds.add(o);
			}else{
				String tid= clist.get(i-1).get("ID").toString();
				if(cid!=null && !"".equals(cid)){
					if(!cid.equals(tid)){
						HashMap o = new HashMap();
						o.put("categorys_id", clist.get((i)).get("ID"));
						o.put("categorys_name", clist.get((i)).get("CATEGORYS_NAME"));
						breeds.add(o);
					}
				}
			}
		}
		//遍历大类
		for(Map m : breeds){
			List<Object> firstListBreed = new ArrayList<Object>();
			List<Object> secondListBreed = new ArrayList<Object>();
			List<Object> thirdListBreed = new ArrayList<Object>();
			List<Object> fourthListBreed = new ArrayList<Object>();
			List<Object> listBreed = new ArrayList<Object>();
			//声明对象
			Map <String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("categorys_id", m.get("categorys_id").toString());
			resultMap.put("categorys_name", m.get("categorys_name").toString());
			for(Map c : clist){
				if(m.get("categorys_id")!=null && m.get("categorys_id").toString().equals(c.get("ID").toString())){
					int temp =0;
					if(c.get("BREED_INITIAL")!=null ) temp = (int) c.get("BREED_INITIAL").toString().toCharArray()[0];
					if(temp>=A && temp<=F){
						firstListBreed.add(c);
					}else if(temp>=G && temp<=L){
						secondListBreed.add(c);
					}else if(temp>=M && temp<=R){
						thirdListBreed.add(c);
					}else if(temp>=S && temp<=Z){
						fourthListBreed.add(c);
					}
				}
			}
			for(Map r : rlist){
				if(m.get("categorys_id")!=null && m.get("categorys_id").toString().equals(r.get("ID").toString())){
					listBreed.add(r);
				}
			}
			resultMap.put("listBreed", listBreed);
			resultMap.put("firstListBreed", firstListBreed);
			resultMap.put("secondListBreed", secondListBreed);
			resultMap.put("thirdListBreed", thirdListBreed);
			resultMap.put("fourthListBreed", fourthListBreed);
			//添加category下面的热门breed
			resultList.add(resultMap);
		}
		long l2 = System.nanoTime();
		log.error("dao run time:: " +(l2-l1)/1000000.000d+" 毫秒");
		/**************************************end*******************************/
		return resultList;
	}

	@Override
	public Map<Object, Object> selectInfoByBreedid(String breedId){
		//1.获取操作的SqlSessionTemplate
		SqlSessionTemplate sqlSession= this.getSqlSession();
		//2.查询出品种的等级
		List breedStandLevelList= sqlSession.selectList("com.jointown.zy.common.dao.SortListDao.selectBreedLevelByBreedId",
				breedId);
		//3.查询出品种的所在仓库
		List listAllWarehouse=sqlSession.selectList("com.jointown.zy.common.dao.SortListDao.selectAllWarehouse");
		//4.查询出产品的产地
		List listBreedPlace=sqlSession.selectList("com.jointown.zy.common.dao.SortListDao.selectBreedPlaceByBreedId",
				breedId);
		//5.查询出该breedId对应的breed
		Map  breed=sqlSession.selectOne("com.jointown.zy.common.dao.SortListDao.selectBreedInfoByBreedId",breedId );
		Map resultMap = new HashMap();
		resultMap.put("breedId", breedId);
		resultMap.put("breed", breed);
		resultMap.put("breedStandLevelList", breedStandLevelList);
		resultMap.put("listAllWarehouse", listAllWarehouse);
		resultMap.put("listBreedPlace", listBreedPlace);
		return resultMap;
	}
	@Override
	public List <Map<Object,Object>> selectBreedByCategorys(String categorysId){
		//1.获取操作的SqlSessionTemplate
		SqlSessionTemplate sqlSession= this.getSqlSession();
		Map <String,Object> queryMap = new HashMap <String,Object>();
		queryMap.put("id", categorysId);
		//查询出该品种下面的breed
		List <Map<Object,Object>> listBreed=sqlSession.selectList
				("com.jointown.zy.common.dao.SortListDao.selectBreedByCategorys", queryMap);
		return listBreed;
		
	}

	@Override
	public List<Map<Object, Object>> selectMedicinalByClassName(String className) {
		//1.获取操作的SqlSessionTemplate
		SqlSessionTemplate sqlSession= this.getSqlSession();
		//2.获取className下面的所有的categorys
		List <Map<Object,Object>> listCategory=
				sqlSession.selectList("com.jointown.zy.common.dao.SortListDao.selectCategorysByClassName",className);
		//3.设置赶回的list
		List resultList = new ArrayList();
		//4.遍历形态分类下面的所有的categorys
		for(int i=0;i<listCategory.size();i++){
			//5.获取categorys_name
			Map categorys = listCategory.get(i);
			String categorys_name="";
			if(categorys.get("BREED_NAME")!=null){
				categorys_name=categorys.get("BREED_NAME").toString();
			}
			//6.categorys_name不为空时进行操作
			if(!StringUtils.isEmpty(categorys_name)){
				//7.通过query
				String id= categorys.get("BREED_ID").toString();
				Map <String,Object>resultMap = new HashMap<String,Object>();
				//将返回的id
				resultMap.put("breed_id", id);
				resultMap.put("categorys_name", categorys_name);
				resultList.add(resultMap);
			}
		}
		return resultList;
	}

}
