package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;
public interface SortListService {
	public List<Map <Object,Object>>queryAllMedicinal();
	public List<Map <Object,Object>>queryMedicinalByClassName(String className);
	public Map selectInfoByBreedid(String breedId);
	public List <Map<Object,Object>> selectBreedByCategorys(String id);
}
