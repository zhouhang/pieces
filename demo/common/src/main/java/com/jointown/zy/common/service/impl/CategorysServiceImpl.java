package com.jointown.zy.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jointown.zy.common.dao.BreedCategoryDao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jointown.zy.common.dao.BreedDao;
import com.jointown.zy.common.dao.CategorysDao;
import com.jointown.zy.common.dao.ClassInfoDao;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BreedCategory;
import com.jointown.zy.common.model.Categorys;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.ClassInfo;
import com.jointown.zy.common.service.CategorysService;

@Service("categorysService")
public class CategorysServiceImpl implements CategorysService {
	private static final Logger log = LoggerFactory.getLogger(CategorysServiceImpl.class);
	
	@Autowired
	private CategorysDao categorysDao;
	@Autowired
	private BreedDao breedDao;
	@Autowired
	private BreedCategoryDao breedCategoryDao;
	@Autowired 
	private ClassInfoDao classInfoDao;
	
	@Transactional
	@Override
	public int addCategory(Categorys category) {
		List<Categorys> categorys = getCategoryByClassAndName(category.getClassId(),category.getCategorysName(),null);
			if(categorys != null && categorys.size()>0)
				return 0;
		long id = categorysDao.selectCategorysSeq(); //主键值
		long parentId =  category.getParentId();
		//根目录
		if(parentId == 0l)
		{
			category.setCategorysLevel(Short.valueOf("1"));
			category.setCategorysPath(id+"");
		}
		else{
			Categorys parentCategory = categorysDao.selectByPrimaryKey(parentId);
				category.setCategorysLevel((short)(parentCategory.getCategorysLevel().shortValue()+Short.parseShort("1")));
				category.setCategorysPath(parentCategory.getCategorysPath()+"/"+id);
		}
		category.setId(id);
		category.setCreateTime(new Date());
		category.setIsLeaf(Short.valueOf("1")); //新添加的节点为叶子节点
		category.setStatus(Short.valueOf("1")); //有效
		List<Categorys> categoryList = getChildNode(parentId,category.getClassId());
		if(categoryList != null && categoryList.size()>0){
			category.setCategorysOrder(new Long((long)(categoryList.size()+1)));
		}else
			category.setCategorysOrder(1l);
		
		int num  = categorysDao.insert(category);
		//添加成功后，看其父节点的is_leaf字段是否为1，即为叶子结点。若为叶子节点则将其改为非叶子节点，即is_leaf改为0
		if(num>0 && parentId !=0l){
			Categorys parentCategory = categorysDao.selectByPrimaryKey(category.getParentId());
			if(parentCategory.getIsLeaf().equals(Short.valueOf("1"))){
				Categorys parentCategoryTmp = new Categorys();
					parentCategoryTmp.setId(parentId);
					parentCategoryTmp.setIsLeaf(Short.valueOf("0"));
					categorysDao.updateByPrimaryKeySelective(parentCategoryTmp);
			}
				
		}
		
		return num;
	}
	
	public List<Categorys> getChildNode(Long categoryId,Long classId){
		
//		Categorys currentCate = categorysDao.selectByPrimaryKey(categoryId);
		Categorys category = new Categorys();
			category.setParentId(categoryId);
			category.setStatus(Short.valueOf("1"));
			category.setClassId(classId);
			return categorysDao.selectList(category);
	}

	@Override
	public int updateCategoryName(Long categoryId, String categoryName,Long userId) {
		Categorys cate = categorysDao.selectByPrimaryKey(categoryId);
		//如果修改后的类目名称与之前的一致，则不操作数据库修改记录了
		if(cate != null && cate.getCategorysName().equals(categoryName))
			return 1;
		//如果修改后的类目名称与其所在分类下的其他类目同名称，则修改失败
		List<Categorys> categorys = getCategoryByClassAndName(cate.getClassId(),categoryName,categoryId);
		if(categorys != null && categorys.size()>0)
			return 0;
		Categorys category = new Categorys();
			category.setId(categoryId);
			category.setCategorysName(categoryName);
			category.setUpdater(userId);
			category.setUpdateTime(new Date());
		return categorysDao.updateByPrimaryKeySelective(category);
	}

	@Override
	@Transactional
	public int deleteCategory(Long categoryId, Long userId,Long classId) {
		int num = 0;
		//有子结点不能删除
		List<Categorys> childCategorys = getChildNode(categoryId,classId);
		if(childCategorys != null && childCategorys.size()>0)
			return num;
		//挂品种不能删除
		List<Breed> breedList = breedDao.selectBreedByCat(categoryId);
		if(breedList != null && breedList.size()>0)
			return num;
		Categorys category = new Categorys();
			category.setId(categoryId);
			category.setStatus(Short.valueOf("0"));
			category.setUpdater(userId);
			category.setUpdateTime(new Date());
			num =  categorysDao.updateByPrimaryKeySelective(category);
			//查看其父节点是否还有子目录，如果没有 将其父节点的IS_LEAF更改为1 即叶子节点
			if(num>0){
				Categorys currentCategory = categorysDao.selectByPrimaryKey(categoryId);
				Long parentId = currentCategory.getParentId();
				List<Categorys> pChildCategorys = getChildNode(parentId,classId);
				if(!(pChildCategorys!=null && pChildCategorys.size()>0)){
					Categorys pCategory = new Categorys();
						pCategory.setId(parentId);
						pCategory.setIsLeaf(Short.valueOf("1"));
						pCategory.setUpdater(userId);
						pCategory.setUpdateTime(new Date());
						categorysDao.updateByPrimaryKeySelective(pCategory);
				}
			}
			return num;
	}

	@Override
	@Transactional
	public int updateCategoryOrder(Long categoryId, Long userId, Long targetId) {
		int num = 0;
		Categorys category = categorysDao.selectByPrimaryKey(categoryId);
		Categorys targetCategory = categorysDao.selectByPrimaryKey(targetId);
		int seq = targetCategory.getCategorysOrder().intValue();
		int position = category.getCategorysOrder().intValue(); 
		//如果顺序没有改变则不做操作
		if(category != null && category.getCategorysOrder().intValue()==seq)
			return num;
		Categorys cat = new Categorys();
			cat.setId(categoryId);
			cat.setCategorysOrder(new Long(seq));
			cat.setUpdater(userId);
			cat.setUpdateTime(new Date());
			num = categorysDao.updateByPrimaryKeySelective(cat);
			if(num>0){
				if(position>seq){
					Categorys categorys = new Categorys();
						categorys.setStatus(Short.valueOf("1"));
						categorys.setParentId(category.getParentId());
						categorys.setSqlStr(" and CATEGORYS_ORDER<"+position+" and CATEGORYS_ORDER>="+seq+" and ID!="+categoryId);
					List<Categorys> categoryList = categorysDao.selectList(categorys);
					for(Categorys cate:categoryList){
						updateCategoryOrderById(cate.getId(),new Long(cate.getCategorysOrder().intValue()+1),userId);
					}
				}else if(position<seq){
					Categorys categorys = new Categorys();
					categorys.setStatus(Short.valueOf("1"));
					categorys.setParentId(category.getParentId());
					categorys.setSqlStr(" and CATEGORYS_ORDER>"+position+" and CATEGORYS_ORDER<="+seq+" and ID!="+categoryId);
				List<Categorys> categoryList = categorysDao.selectList(categorys);
				for(Categorys cate:categoryList){
					updateCategoryOrderById(cate.getId(),new Long(cate.getCategorysOrder().intValue()-1),userId);
				}
				}
			}
		return num;
	}
	
	private int updateCategoryOrderById(Long categoryId,Long pos,Long userId){
		Categorys category = new Categorys();
			category.setId(categoryId);
			category.setCategorysOrder(pos);
			category.setUpdater(userId);
			category.setUpdateTime(new Date());
			return categorysDao.updateByPrimaryKeySelective(category);
			
	}

	@Override
	public List<Categorys> selectCategoryBy(Long classId) {
		Categorys category = new Categorys();
			category.setClassId(classId);
			category.setStatus(Short.valueOf("1"));
			category.setSqlSort(" CATEGORYS_LEVEL asc,CATEGORYS_ORDER asc");
			return categorysDao.selectList(category);
		 
	}

	@Override
	public List<Map<String,Object>> getBreedByCategory(Page<Map<String,Object>> page) {
		List<Map<String,Object>>  breedInfoList = breedDao.selectBreedInfoBy(page);
		if(breedInfoList != null && breedInfoList.size()>0)
		{
			for(Map<String,Object> map:breedInfoList){
				//类目路径 
				StringBuffer pathBuff = new StringBuffer();
				if(map!=null && map.get("CATEGORYS_PATH")!=null){
					String[] categoryIds = map.get("CATEGORYS_PATH").toString().split("/");
					for(String categoryIdStr:categoryIds){
						if(StringUtils.isNotBlank(categoryIdStr)){
							Categorys cat = categorysDao.selectByPrimaryKey(Long.valueOf(categoryIdStr));
							if(cat != null)
							  pathBuff.append(cat.getCategorysName()).append("/");
						}
					}
				}
				if(pathBuff != null && pathBuff.length()>0)
				{
					String path = pathBuff.toString().substring(0, pathBuff.toString().length()-1);
					map.put("path", path);
				}
			}
		}
		return breedInfoList;
	}
	
	public List<Categorys> getCategoryByClassAndName(Long classId,String name,Long categoryId){
		Categorys category = new Categorys();
			category.setClassId(classId);
			category.setCategorysName(name);
			category.setStatus(Short.valueOf("1"));
			if(categoryId != null)
				category.setSqlStr(" and ID!="+categoryId);
			return categorysDao.selectList(category);
	}
	/**
	 * @author chengchang
	 * @return 将每个class_info（分类表）下面的所有的分类当做一棵树进行返回，生成多棵树
	 */
	@Override
	public List<Object> getAllTree() {
		//1.查询出class_info下面有多少个主类目就会产生多少颗树(查询有效的主类目)
		 ClassInfo classInfoq = new ClassInfo();
		 classInfoq.setStatus(Short.parseShort("1"));
		 List <ClassInfo> classInfos=classInfoDao.selectList(classInfoq);
		 List <Object> allTrees = new ArrayList <Object>();
		 if(classInfos!=null&&classInfos.size()>0){
			 for(ClassInfo classInfo :classInfos){
				 //获取每个classInfo的ID
				 long id=classInfo.getClassId();
				 //通过每个classId查询出该classId下面的所有的categorys
				 Categorys categorys = new Categorys();
				 categorys.setClassId(id);
				 categorys.setStatus(Short.parseShort("1"));
				 categorys.setSqlSort("CATEGORYS_ORDER asc");
				 List<Categorys> listCategorys =categorysDao.selectList(categorys);
				 if(listCategorys!=null&&listCategorys.size()>0){
					 //将该listCategory封装成一棵树
					 Map treeMap= getCategorysTree(listCategorys, classInfo);
					 allTrees.add(treeMap);
				 }
			 }
		 }
		 return allTrees;
	}

	@Override
	public void associateCategoryAndBreed(Long categoryId, String[] breedArray) {
		if(breedArray==null||breedArray.length==0)
			return ;
		for(int i=0;i<breedArray.length;i++){
			if(StringUtils.isBlank(breedArray[i]))
				continue;
			BreedCategory bc = new BreedCategory();
				bc.setCategorysId(categoryId);
				bc.setBreedId(Long.valueOf(breedArray[i]));
				bc.setStatus(Short.valueOf("1"));
				breedCategoryDao.insert(bc);
		}
	}

	@Override
	public int deleteCategoryAndBreedRela(Long breedCategoryId) {
		return breedCategoryDao.deleteByPrimaryKey(breedCategoryId);
	}


	/**@author chengchang
	 * @description 传递classInfo和classInfo下面的listCategorys生成一棵树为json的格式
	 * @param listCategorys
	 * @param classInfo
	 * @return 返回一个json格式的树
	 */
	public Map getCategorysTree(List<Categorys> listCategorys ,ClassInfo classInfo){
		JsonArray jsonArray = new JsonArray();
		//树的头部
		/*JsonObject firstJsonObject = new JsonObject();
		firstJsonObject.addProperty("id",classInfo.getClassId());
		firstJsonObject.addProperty("pId", 0);
		firstJsonObject.addProperty("name", classInfo.getClassName());
		jsonArray.add(firstJsonObject);*/
		//树的身子
		for(int i=0;i<listCategorys.size();i++){
			JsonObject jsonObject = new JsonObject();
			/*if(i==0){
				jsonObject.addProperty("id",listCategorys.get(i).getId());
				jsonObject.addProperty("pId", classInfo.getClassId());
				jsonObject.addProperty("name", listCategorys.get(i).getCategorysName());
			}else{*/
				jsonObject.addProperty("id",listCategorys.get(i).getId());
				jsonObject.addProperty("pId", listCategorys.get(i).getParentId());
				jsonObject.addProperty("name", listCategorys.get(i).getCategorysName());
			//}
			jsonArray.add(jsonObject);
		}
		//将jsonArray生成一个json对象
		Gson gson = new Gson();
		String json = gson.toJson(jsonArray);
		Map treeMap = new HashMap();
		treeMap.put("classInfo",classInfo.getClassName());
		treeMap.put("categorys", json);
		return treeMap;
	}

	@Override
	public List<Breed> selectBreedByCat(Long categoryId) {
		return breedDao.selectBreedByCat(categoryId);
	}

	@Override
	public Categorys getCategorysById(Long categoryId) {
		return categorysDao.selectByPrimaryKey(categoryId);
	}
	
	public List<Breed> getNotAssociateBreedBy(Page page){
		return breedDao.selectBreedByCategory(page);
	}
	/**
	 * @param breed
	 * @param chkDisabled true表示复选框不可以，false表示复选框可用
	 * @description 传递一个品种，可以查询出所有的树，然后通过breed和categorys的关系，生成要求的数据
	 * 显示在前台为多选框已经按照要求选择了
	 */
	 
	@Override
	public List<Object> getAllTreeAndChecked(Breed breed,boolean chkDisabled) {
		//查询出breed和Category的关系listBreedCategory
		List <BreedCategory> listBreedCategorys= breedCategoryDao.selectByBreedId(breed.getBreedId());
		//1.查询出class_info下面有多少个主类目就会产生多少颗树(查询有效的主类目)
		 ClassInfo classInfoq = new ClassInfo();
		 classInfoq.setStatus(Short.parseShort("1"));
		 List <ClassInfo> classInfos=classInfoDao.selectList(classInfoq);
		 List <Object> allTrees = new ArrayList <Object>();
		 if(classInfos!=null&&classInfos.size()>0){
			 for(ClassInfo classInfo :classInfos){
				 //获取每个classInfo的ID
				 long id=classInfo.getClassId();
				 //通过每个classId查询出该classId下面的所有的categorys
				 Categorys categorys = new Categorys();
				 categorys.setClassId(id);
				 categorys.setStatus(Short.parseShort("1"));
				 categorys.setSqlSort("CATEGORYS_ORDER asc");
				 List<Categorys> listCategorys =categorysDao.selectList(categorys);
				 //if(listBreedCategorys!=null&&listBreedCategorys.size()>0){
					 //将该listCategory封装成一棵树
					 Map treeMap= getCategorysTreeChecked(listCategorys, classInfo,listBreedCategorys,chkDisabled);
					 allTrees.add(treeMap);
				 //}
			 }
		 }
		return allTrees;
	}
	
	/**@author chengchang
	 * @description 传递classInfo和classInfo下面的listCategorys生成一棵树为json的格式
	 * @param listCategorys
	 * @param classInfo
	 * @param listBreedCategory  (breed下面的所有的categorys关系)
	 * @return 返回一个json格式的树
	 */
	public Map getCategorysTreeChecked(List<Categorys> listCategorys ,ClassInfo classInfo,List <BreedCategory> listBreedCategory ,boolean chkDisabled){
		JsonArray jsonArray = new JsonArray();
		//树的身子
		for(int i=0;i<listCategorys.size();i++){
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("id",listCategorys.get(i).getId());
			jsonObject.addProperty("pId", listCategorys.get(i).getParentId());
			jsonObject.addProperty("name", listCategorys.get(i).getCategorysName());
			for(BreedCategory breedCategory:listBreedCategory){
				//breedCategory的categoryId和listCategorys中一致，表示这个被选中了
				if(breedCategory.getCategorysId().equals(listCategorys.get(i).getId())){
					jsonObject.addProperty("checked", true);
					//匹配成功后进行下一个循环
					break;
				}
			}
			if(chkDisabled){
				jsonObject.addProperty("chkDisabled", true);
			}
			jsonArray.add(jsonObject);
		}
		//将jsonArray生成一个json对象
		Gson gson = new Gson();
		String json = gson.toJson(jsonArray);
		Map treeMap = new HashMap();
		treeMap.put("classInfo",classInfo.getClassName());
		treeMap.put("categorys", json);
		return treeMap;
	}


	@Override
	public List<Categorys> getCategoryByName(Long breedId) {
		return  categorysDao.getCategorysByNameAndClassName(breedId);
	}


	@Override
	public Categorys selectByPrimaryKey(Long id) {
		Categorys category = categorysDao.selectByPrimaryKey(id);
		return category;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean updateIndex(String[] array) {
		int i=0;
		boolean flag = false;
		for(String str:array){
			String[] obj = str.split(":");
			HashMap map = new HashMap();
			map.put("id", obj[0]);
			map.put("cindex", obj[1]);
			int j = 0;
			try{
				j = breedCategoryDao.updateIndex(map);
			}catch(Exception e){
				return flag;
			}
			if(j>0) i++;
		}
		if(i == array.length) flag =true;
		return flag;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int getExistRules(String cindex,String categorysid) {
		int rules = 0;
		try{
			HashMap map = new HashMap();
			map.put("categorysid", (categorysid!=null && !"".equals(categorysid))?Long.parseLong(categorysid):0L);
			map.put("cindex", (cindex!=null && !"".equals(cindex))?Integer.parseInt(cindex):0);
			rules = breedCategoryDao.selectRules(map);
		}catch(NullPointerException e){
			log.error("CategorysServiceImpl.getExistRules==> NullPointerException");
		}
		return rules;
	}
}