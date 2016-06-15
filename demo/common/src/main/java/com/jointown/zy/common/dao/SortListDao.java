package com.jointown.zy.common.dao;
/**
 * @description 列表分类的头操作
 */
import java.util.List;
import java.util.Map;

public interface SortListDao {
	public List<Map<Object, Object>> selectAllMedicinal();
	public List<Map<Object, Object>> selectMedicinalByClassName(String className);
	public Map <Object,Object> selectInfoByBreedid(String breedId);
	//根据categorysId查询出下面的breed
	public List <Map<Object,Object>> selectBreedByCategorys(String id);
}
