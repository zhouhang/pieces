package com.jointown.zy.common.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.CmsArticleDataDao;
import com.jointown.zy.common.dao.CmsArticleSourceDao;
import com.jointown.zy.common.dto.CmsArticleDto;
import com.jointown.zy.common.exception.CmsArticleException;
import com.jointown.zy.common.exception.ErrorException;
import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.model.CmsArticleData;
import com.jointown.zy.common.model.CmsArticleSource;
import com.jointown.zy.common.service.CmsArticleService;
import com.jointown.zy.common.vo.CmsArticleSourceVo;
/**
 * 文章ServiceImpl
 * @author Mr.songwei
 * @date 2014年11月25日下午4:13:44
 */
@Service
public class CmsArtServiceImpl implements CmsArticleService {
	@Autowired
	private CmsArticleDataDao artdataDao;
	@Autowired
	private CmsArticleSourceDao artsourceDao;

	/**添加文章列表，文章详情信息*/
	@Override
	public void addArt(CmsArticleDto cmsartdto) throws ErrorException{
		if(cmsartdto==null){
    		throw new CmsArticleException(new ErrorMessage(ErrorRepository.CMS_NULLPOINTER)); 
    	}
		try{
			CmsArticleData art_data = new CmsArticleData();
			art_data.setContent(cmsartdto.getContent());
			art_data.setCopyfrom(cmsartdto.getCopyfrom());
			artdataDao.insert(art_data);
			CmsArticleSource art_source = new CmsArticleSource();
			art_source.setTitle(cmsartdto.getTitle());
			art_source.setArtDataId(art_data.getId());
			art_source.setCategoryId(cmsartdto.getCategory_id());
			art_source.setDescription(cmsartdto.getDescription());
			art_source.setCreateDate(cmsartdto.getCreate_date());
			art_source.setImage(cmsartdto.getFile_id());
			art_source.setCreateBy(cmsartdto.getCopyfrom());
			art_source.setKeywords(cmsartdto.getKeywords());
			artsourceDao.insertSelective(art_source);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * 查询所有的文章，以及对应的栏目名称
     * 参数:HashMap<String,String> 包括查询日期，文章标题，关键字
     * 返回:List<CmsArticleSourceVo>
     * @ibatorgenerated 2014-11-26 09:46:32
     */
    @Override
	public List<CmsArticleSourceVo> selectAll(HashMap<String,String> queryString){
    	return artsourceDao.selectAll(queryString);
    }
}
