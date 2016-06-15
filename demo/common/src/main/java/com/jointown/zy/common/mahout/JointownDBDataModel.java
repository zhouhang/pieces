/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.mahout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.FastIDSet;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.AbstractDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericBooleanPrefDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;

import com.jointown.zy.common.model.PreferenceData;

/**
 * @ClassName: JointownDBDataModel
 * @Description: TODO
 * @Author: robin.liu
 * @Date: 2015年8月17日
 * @Version: 1.0
 */
public class JointownDBDataModel extends AbstractDataModel {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 基本datamodel 包含 userId, itemId, timestamps信息
	 */
	private final GenericDataModel base;
	
	private List<PreferenceData> sourceData;
	
	
	/**
	 * 根据List<PreferenceData>构造DataModel对象
	 * @param sourceData
	 */
	public JointownDBDataModel(List<PreferenceData> sourceData) throws Exception {
		this.sourceData = sourceData;
		this.base = new GenericDataModel(toDataMap(sourceData, true));
		setMinPreference(base.getMinPreference());
	    setMaxPreference(base.getMaxPreference());
	}
	
	
//	public static FastByIDMap<FastIDSet> toDataMap(List<PreferenceData> sourceData,boolean byUser) throws TasteException {
//		FastByIDMap<FastIDSet> data = new FastByIDMap<FastIDSet>(sourceData.size());
//		for(PreferenceData pData:sourceData){
//			long userId = pData.getUserId();
//			if(!data.containsKey(userId)){
//				data.put(userId, new FastIDSet());
//			}
//			data.get(userId).add(pData.getItemId());
//		}
//    	return data;
//   }
	
	/**
	 * 
	 * @Description: 根据数据源转换成FastByIDMap<PreferenceArray>数据
	 * @Author: robin.liu
	 * @Date: 2015年8月18日
	 * @param sourceData
	 * @return
	 * @throws TasteException
	 */
	public static FastByIDMap<PreferenceArray> toDataMap(List<PreferenceData> sourceData,boolean byUser) throws TasteException {
		FastByIDMap<Collection<Preference>> data = new FastByIDMap<Collection<Preference>>(sourceData.size());
		for(PreferenceData pData:sourceData){
			long userId = pData.getUserId();
			if(!data.containsKey(userId)){
				data.put(userId, new ArrayList<Preference>());
			}
			data.get(userId).add(new GenericPreference(pData.getUserId(),pData.getItemId(),pData.getTimes()));
		}
    	return GenericDataModel.toDataMap(data, byUser);
   }

	@Override
	public LongPrimitiveIterator getUserIDs() throws TasteException {
		return base.getUserIDs();
	}

	@Override
	public PreferenceArray getPreferencesFromUser(long userID)
			throws TasteException {
		return base.getPreferencesFromUser(userID);
	}

	@Override
	public FastIDSet getItemIDsFromUser(long userID) throws TasteException {
		return base.getItemIDsFromUser(userID);
	}

	@Override
	public LongPrimitiveIterator getItemIDs() throws TasteException {
		return base.getItemIDs();
	}

	@Override
	public PreferenceArray getPreferencesForItem(long itemID)
			throws TasteException {
		return base.getPreferencesForItem(itemID);
	}

	/**
	 * 根据userId, itemId获取preference值
	 */
	@Override
	public Float getPreferenceValue(long userID, long itemID)
			throws TasteException {
		return base.getPreferenceValue(userID, itemID);
	}

	@Override
	public Long getPreferenceTime(long userID, long itemID)
			throws TasteException {
		return base.getPreferenceTime(userID, itemID);
	}
	
	@Override
	public int getNumItems() throws TasteException {
		return base.getNumItems();
	}

	@Override
	public int getNumUsers() throws TasteException {
		return base.getNumUsers();
	}

	@Override
	public int getNumUsersWithPreferenceFor(long itemID) throws TasteException {
		return base.getNumUsersWithPreferenceFor(itemID);
	}

	@Override
	public int getNumUsersWithPreferenceFor(long itemID1, long itemID2)
			throws TasteException {
		return base.getNumUsersWithPreferenceFor(itemID1, itemID2);
	}
	
	@Override
	public void removePreference(long userID, long itemID) {
	   throw new UnsupportedOperationException();
	}
	  
	@Override
	public void setPreference(long userID, long itemID, float value) {
	    throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasPreferenceValues() {
		return base.hasPreferenceValues();
	}

	
	@Override
	public void refresh(Collection<Refreshable> alreadyRefreshed) {
		base.refresh(alreadyRefreshed);
		
	}

	public GenericDataModel getBaseDataModel() {
		return base;
	}
	
//	public GenericBooleanPrefDataModel getBaseDataModel() {
//		return base;
//	}

}
