/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.mahout;

import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.jointown.zy.common.model.PreferenceData;

/**
 * @ClassName: MahoutManager
 * @Description: Mahout相关的操作
 * @Author: robin.liu
 * @Date: 2015年8月19日
 * @Version: 1.0
 */
public class MahoutManager {
	
	/**
	 * 
	 * @Description: 根据用户名 和 数据 推荐商品
	 * @Author: robin.liu
	 * @Date: 2015年8月19日
	 * @param userId
	 * @param sourceData
	 * @param isUserBased
	 * @return
	 * @throws Exception
	 */
	public static List<String> recommendListingByUserId(Long userId,List<PreferenceData> sourceData,boolean isUserBased) throws Exception {
		final int NEIGHBORHOOD_NUM = 5;//邻居数目
		final int RECOMMENDER_NUM = 10;//推荐物品数目
		final boolean isUserBasedRecommend = isUserBased;
		// 构造data
		JointownDBDataModel model = new JointownDBDataModel(sourceData);
		// 构造推荐builder
        RecommenderBuilder builder = new RecommenderBuilder(){
            @Override
            public Recommender buildRecommender(DataModel model)
                    throws TasteException {
            	if(isUserBasedRecommend){
            		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            		UserNeighborhood neighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, similarity, model);
            		return new  GenericUserBasedRecommender(model, neighborhood, similarity);
            	}else{
            		ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
            		return new GenericItemBasedRecommender(model, similarity);
            	}
            }
        };
        List<String> result = new ArrayList<String>();
        List<RecommendedItem> recList = builder.buildRecommender(model).recommend(userId, RECOMMENDER_NUM);
        for (RecommendedItem recItem : recList) {
        	result.add(new Long(recItem.getItemID()).toString());
        }
		return result;
	}

}
