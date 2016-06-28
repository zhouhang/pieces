package com.jointown.zy.common.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Session;
import com.jointown.zy.common.dao.BusiQualityInfoDao;
import com.jointown.zy.common.dao.BusiQualityPicDao;
import com.jointown.zy.common.dao.BusiWhlistDao;
import com.jointown.zy.common.dao.BusiWhlistLogDao;
import com.jointown.zy.common.dto.BusiQualityInfoDto;
import com.jointown.zy.common.dto.BusiQualityPicDto;
import com.jointown.zy.common.dto.BusiWhlistDto;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.enums.SolrContentTypeEnum;
import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.model.BusiQualityItem;
import com.jointown.zy.common.model.BusiQualityinfo;
import com.jointown.zy.common.model.BusiQualitypic;
import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.model.BusiWhlistLog;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.BreedVo;
import com.jointown.zy.common.vo.BusiGoodsInfoVo;
import com.jointown.zy.common.vo.BusiWhlistVo;
import com.jointown.zy.common.vo.CategorysVo;
import com.jointown.zy.common.vo.DictInfoVo;

/**
 * 仓单管理ServiceImpl
 * @author wangjunhu
 * 2014-12-19
 */
@Service
public class BusiWhlistServiceImpl implements BusiWhlistService {

	@Autowired
	private BusiWhlistDao busiWhlistDao;
	
	@Autowired
	private BusiWhlistLogDao busiWhlistLogDao;

	@Autowired
	private BusiQualityInfoDao busiQualityInfoDao;
	@Autowired
	private BusiQualityPicDao busiQualityPicDao;
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;
	
	@Override
	public void addBusiWhlist(SFTPUtil sftp,Session session,BusiWhlistDto busiWhlistDto, BusiQualityInfoDto busiQualityInfoDto, BusiQualityPicDto busiQualityPicDto) {
		BusiWhlist busiWhlist = busiWhlistDto.getBusiwhlist();
		busiWhlistDao.insertBusiWhlist(busiWhlist);

		BusiQualityinfo busiQualityInfo = busiQualityInfoDto.getBusiqualityinfo();
		busiQualityInfoDao.insertSelective(busiQualityInfo);
		
		BusiQualitypic busiQualitypic = uploadBusiQualityPic(sftp,session,busiQualityPicDto).getBusiqualitypic();
		
		String file1 = busiQualityPicDto.getFile1();
		if(file1!=null&&!file1.isEmpty()){
			busiQualitypic.setPath(file1);
			busiQualitypic.setPicindex((short) 1);
			busiQualityPicDao.insertSelective(busiQualitypic);
		}
		String file2 = busiQualityPicDto.getFile2();
		if(file2!=null&&!file2.isEmpty()){
			busiQualitypic.setPath(file2);
			busiQualitypic.setPicindex((short) 2);
			busiQualityPicDao.insertSelective(busiQualitypic);
		}
		String file3 = busiQualityPicDto.getFile3();
		if(file3!=null&&!file3.isEmpty()){
			busiQualitypic.setPath(file3);
			busiQualitypic.setPicindex((short) 3);
			busiQualityPicDao.insertSelective(busiQualitypic);
		}
		String file4 = busiQualityPicDto.getFile4();
		if(file4!=null&&!file4.isEmpty()){
			busiQualitypic.setPath(file4);
			busiQualitypic.setPicindex((short) 4);
			busiQualityPicDao.insertSelective(busiQualitypic);
		}
		String file5 = busiQualityPicDto.getFile5();
		if(file5!=null&&!file5.isEmpty()){
			busiQualitypic.setPath(file5);
			busiQualitypic.setPicindex((short) 5);
			busiQualityPicDao.insertSelective(busiQualitypic);
		}
		String file6 = busiQualityPicDto.getFile6();
		if(file6!=null&&!file6.isEmpty()){
			busiQualitypic.setPath(file6);
			busiQualitypic.setPicindex((short) 6);
			busiQualityPicDao.insertSelective(busiQualitypic);
		}
		String file7 = busiQualityPicDto.getFile7();
		if(file7!=null&&!file7.isEmpty()){
			busiQualitypic.setPath(file7);
			busiQualitypic.setPicindex((short) 7);
			busiQualityPicDao.insertSelective(busiQualitypic);
		}
	}

	@Override

	public void alterBusiWhlist(SFTPUtil sftp,Session session,BusiWhlistDto busiWhlistDto, BusiQualityInfoDto busiQualityInfoDto, BusiQualityPicDto busiQualityPicDto) {
		BusiWhlist busiWhlist = busiWhlistDto.getBusiwhlist();
		busiWhlistDao.updateBusiWhlistById(busiWhlist);
		
		BusiQualityinfo busiQualityInfo = busiQualityInfoDto.getBusiqualityinfo();
		int busiQualityInfoOk = busiQualityInfoDao.updateByWLIDSelective(busiQualityInfo);
		if(busiQualityInfoOk==0){
			busiQualityInfoDao.insertSelective(busiQualityInfo);
		}
		
		BusiQualitypic busiQualitypic = uploadBusiQualityPic(sftp,session,busiQualityPicDto).getBusiqualitypic();
		String file1 = busiQualityPicDto.getFile1();
		if(file1!=null&&!file1.isEmpty()){
			busiQualitypic.setPath(file1);
			busiQualitypic.setPicindex((short) 1);
			int busiQualityPicOk = busiQualityPicDao.updateByWlIdAndPicIndex(busiQualitypic);
			if(busiQualityPicOk==0){
				busiQualityPicDao.insertSelective(busiQualitypic);
			}
		}
		String file2 = busiQualityPicDto.getFile2();
		if(file2!=null&&!file2.isEmpty()){
			busiQualitypic.setPath(file2);
			busiQualitypic.setPicindex((short) 2);
			int busiQualityPicOk = busiQualityPicDao.updateByWlIdAndPicIndex(busiQualitypic);
			if(busiQualityPicOk==0){
				busiQualityPicDao.insertSelective(busiQualitypic);
			}
		}
		String file3 = busiQualityPicDto.getFile3();
		if(file3!=null&&!file3.isEmpty()){
			busiQualitypic.setPath(file3);
			busiQualitypic.setPicindex((short) 3);
			int busiQualityPicOk = busiQualityPicDao.updateByWlIdAndPicIndex(busiQualitypic);
			if(busiQualityPicOk==0){
				busiQualityPicDao.insertSelective(busiQualitypic);
			}
		}
		String file4 = busiQualityPicDto.getFile4();
		if(file4!=null&&!file4.isEmpty()){
			busiQualitypic.setPath(file4);
			busiQualitypic.setPicindex((short) 4);
			int busiQualityPicOk = busiQualityPicDao.updateByWlIdAndPicIndex(busiQualitypic);
			if(busiQualityPicOk==0){
				busiQualityPicDao.insertSelective(busiQualitypic);
			}
		}
		String file5 = busiQualityPicDto.getFile5();
		if(file5!=null&&!file5.isEmpty()){
			busiQualitypic.setPath(file5);
			busiQualitypic.setPicindex((short) 5);
			int busiQualityPicOk = busiQualityPicDao.updateByWlIdAndPicIndex(busiQualitypic);
			if(busiQualityPicOk==0){
				busiQualityPicDao.insertSelective(busiQualitypic);
			}
		}
		String file6 = busiQualityPicDto.getFile6();
		if(file6!=null&&!file6.isEmpty()){
			busiQualitypic.setPath(file6);
			busiQualitypic.setPicindex((short) 6);
			int busiQualityPicOk = busiQualityPicDao.updateByWlIdAndPicIndex(busiQualitypic);
			if(busiQualityPicOk==0){
				busiQualityPicDao.insertSelective(busiQualitypic);
			}
		}
		String file7 = busiQualityPicDto.getFile7();
		if(file7!=null&&!file7.isEmpty()){
			busiQualitypic.setPath(file7);
			busiQualitypic.setPicindex((short) 7);
			int busiQualityPicOk = busiQualityPicDao.updateByWlIdAndPicIndex(busiQualitypic);
			if(busiQualityPicOk==0){
				busiQualityPicDao.insertSelective(busiQualitypic);
			}
		}
		
		RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.WHLIST, busiWhlist.getWlId());
	}
	
	@Override
	public void removeBusiWhlist(BusiWhlistDto busiWhlistDto){
		BusiWhlist busiWhlist = busiWhlistDto.getBusiwhlist();
		busiWhlistDao.deleteBusiWhlistById(busiWhlist);
	};
	
	@Override
	public List<BusiWhlist> findBusiWhlists() {
		return busiWhlistDao.selectBusiWhlists();
	}

	@Override
	public List<BusiWhlistVo> findBusiWhlistsByCondition(Page<BusiWhlistVo> page) {
		return busiWhlistDao.selectBusiWhlistsByCondition(page);
	}

	@Override
	public BusiWhlistVo findWlIdBySeqBusiWhlist(){
		return busiWhlistDao.selectWlIdBySeqBusiWhlist();
	}
	
	@Override
	public BusiWhlistVo findBusiWhlistById(String wlId,Long userId) {
		BusiWhlistVo vo = null;
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("wlId", wlId);
		map.put("userId", userId);
		vo = busiWhlistDao.selectBusiWhlistById(map);
		if(vo != null){
			BusiQualitypic pic = busiQualityPicDao.selectZJPicByWLID(wlId);
			if(pic != null){
				vo.setZjReportPic(pic.getPath());
			}
			vo.setItemMap(getGroupItem(vo.getBusiQualityItem()));
		}
		return vo;
	}

	@Override
	public List<CategorysVo> findCategorysByTree() {
		return busiWhlistDao.selectCategorysByTree();
	}

	@Override
	public List<CategorysVo> findCategorysByBreedId(Long breedId){
		return busiWhlistDao.selectCategorysByBreedId(breedId);
	}
	
	@Override
	public List<BreedVo> findBreedsByTree() {
		return busiWhlistDao.selectBreedsByTree();
	}

	@Override
	public BreedVo findBreedById(Long breedId) {
		return busiWhlistDao.selectBreedById(breedId);
	}
	
	@Override
	public List<BreedVo> findBreedTreesByCategorysId(Long categorysId) {
		return busiWhlistDao.selectBreedTreesByCategorysId(categorysId);
	}


	@Override
	public DictInfoVo findDictInfoByBreedId(Long breedId) {
		return busiWhlistDao.selectDictInfoByBreedId(breedId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int updateWLsurplusById(HashMap map) {
		return busiWhlistDao.updateWLsurplusById(map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int updateWLsurplus(HashMap map) {
		return busiWhlistDao.updateWLsurplus(map);
	}

	@Override
	public AreaVo findAreaByCode(String firstCode){
		return busiWhlistDao.selectAreaByCode(firstCode);
	}
	
	@Override
	public List<AreaVo> findAreasByFirstCode(String firstCode) {
		return busiWhlistDao.selectAreasByFirstCode(firstCode);
	}

	@Override
	public List<BusiWhlistVo> selectBusiWhlistMohu(Page<BusiWhlistVo> page){
		return busiWhlistDao.selectBusiWhlistMohu(page);
	}
	
	@Override
	public BusiWhlistVo findBusiWhlistByWlId(String wlId){
		return busiWhlistDao.selectBusiWhlistByWlId(wlId);
	}

	@Override
	public String saveBusiQualityPic(SFTPUtil sftp,Session session,String filePath) throws Exception {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("imgpath", filePath);
		map.put("dataDir", sftpConfigInfo.getSftpDataDir());
		map.put("imagesDir", sftpConfigInfo.getSftpImagesDir());
		map.put("projectDir", sftpConfigInfo.getSftpProjectDir());
		map.put("tempProjectDir", sftpConfigInfo.getSftpTempProjectDir());
		sftp.moveImg(session, map);
		
		String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
		String b = filePath.substring(0,filePath.lastIndexOf("/"));
		String dateDir = b.substring(b.lastIndexOf("/")+1);
		String fileNewPath = sftpConfigInfo.getSftpImagesDir() +"/"+ sftpConfigInfo.getSftpProjectDir()+"/"+dateDir+"/"+fileName;
		return fileNewPath;
	}
	
	@Override
	public BusiQualityPicDto uploadBusiQualityPic(SFTPUtil sftp,Session session,BusiQualityPicDto busiQualityPicDto) {
		try {
			String file1 = busiQualityPicDto.getFile1();
			if(file1!=null&&!file1.isEmpty()){
				String file1_120 = file1.substring(0,file1.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpMiddleWidth()+file1.substring(file1.lastIndexOf("."));
				String file1_320 = file1.substring(0,file1.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXBigWidth()+file1.substring(file1.lastIndexOf("."));
				String file1_640 = file1.substring(0,file1.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXXBigWidth()+file1.substring(file1.lastIndexOf("."));
				file1 = saveBusiQualityPic(sftp,session,file1);
				busiQualityPicDto.setFile1(file1);
				saveBusiQualityPic(sftp,session,file1_120);
				saveBusiQualityPic(sftp,session,file1_320);
				saveBusiQualityPic(sftp,session,file1_640);
			}
			String file2 = busiQualityPicDto.getFile2();
			if(file2!=null&&!file2.isEmpty()){
				String file2_120 = file2.substring(0,file2.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpMiddleWidth()+file2.substring(file2.lastIndexOf("."));
				String file2_320 = file2.substring(0,file2.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXBigWidth()+file2.substring(file2.lastIndexOf("."));
				String file2_640 = file2.substring(0,file2.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXXBigWidth()+file2.substring(file2.lastIndexOf("."));
				file2 = saveBusiQualityPic(sftp,session,file2);
				busiQualityPicDto.setFile2(file2);
				saveBusiQualityPic(sftp,session,file2_120);
				saveBusiQualityPic(sftp,session,file2_320);
				saveBusiQualityPic(sftp,session,file2_640);
			}
			String file3 = busiQualityPicDto.getFile3();
			if(file3!=null&&!file3.isEmpty()){
				String file3_120 = file3.substring(0,file3.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpMiddleWidth()+file3.substring(file3.lastIndexOf("."));
				String file3_320 = file3.substring(0,file3.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXBigWidth()+file3.substring(file3.lastIndexOf("."));
				String file3_640 = file3.substring(0,file3.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXXBigWidth()+file3.substring(file3.lastIndexOf("."));
				file3 = saveBusiQualityPic(sftp, session,file3);
				busiQualityPicDto.setFile3(file3);
				saveBusiQualityPic(sftp,session,file3_120);
				saveBusiQualityPic(sftp,session,file3_320);
				saveBusiQualityPic(sftp,session,file3_640);
			}
			String file4 = busiQualityPicDto.getFile4();
			if(file4!=null&&!file4.isEmpty()){
				String file4_120 = file4.substring(0,file4.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpMiddleWidth()+file4.substring(file4.lastIndexOf("."));
				String file4_320 = file4.substring(0,file4.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXBigWidth()+file4.substring(file4.lastIndexOf("."));
				String file4_640 = file4.substring(0,file4.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXXBigWidth()+file4.substring(file4.lastIndexOf("."));
				file4 = saveBusiQualityPic(sftp,session,file4);
				busiQualityPicDto.setFile4(file4);
				saveBusiQualityPic(sftp,session,file4_120);
				saveBusiQualityPic(sftp,session,file4_320);
				saveBusiQualityPic(sftp,session,file4_640);
			}
			String file5 = busiQualityPicDto.getFile5();
			if(file5!=null&&!file5.isEmpty()){
				String file5_120 = file5.substring(0,file5.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpMiddleWidth()+file5.substring(file5.lastIndexOf("."));
				String file5_320 = file5.substring(0,file5.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXBigWidth()+file5.substring(file5.lastIndexOf("."));
				String file5_640 = file5.substring(0,file5.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXXBigWidth()+file5.substring(file5.lastIndexOf("."));
				file5 = saveBusiQualityPic(sftp,session,file5);
				busiQualityPicDto.setFile5(file5);
				saveBusiQualityPic(sftp,session,file5_120);
				saveBusiQualityPic(sftp,session,file5_320);
				saveBusiQualityPic(sftp,session,file5_640);
			}
			String file6 = busiQualityPicDto.getFile6();
			if(file6!=null&&!file6.isEmpty()){
				String file6_120 = file6.substring(0,file6.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpMiddleWidth()+file6.substring(file6.lastIndexOf("."));
				String file6_320 = file6.substring(0,file6.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXBigWidth()+file6.substring(file6.lastIndexOf("."));
				String file6_640 = file6.substring(0,file6.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXXBigWidth()+file6.substring(file6.lastIndexOf("."));
				file6 = saveBusiQualityPic(sftp,session,file6);
				busiQualityPicDto.setFile6(file6);
				saveBusiQualityPic(sftp,session,file6_120);
				saveBusiQualityPic(sftp,session,file6_320);
				saveBusiQualityPic(sftp,session,file6_640);
			}
			String file7 = busiQualityPicDto.getFile7();
			if(file7!=null&&!file7.isEmpty()){
				String file7_120 = file7.substring(0,file7.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpMiddleWidth()+file7.substring(file7.lastIndexOf("."));
				String file7_320 = file7.substring(0,file7.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXBigWidth()+file7.substring(file7.lastIndexOf("."));
				String file7_640 = file7.substring(0,file7.lastIndexOf("."))+"_"+sftpConfigInfo.getSftpXXBigWidth()+file7.substring(file7.lastIndexOf("."));
				file7 = saveBusiQualityPic(sftp,session,file7);
				busiQualityPicDto.setFile7(file7);
				saveBusiQualityPic(sftp,session,file7_120);
				saveBusiQualityPic(sftp,session,file7_320);
				saveBusiQualityPic(sftp,session,file7_640);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return busiQualityPicDto;
	}
	
	/**
	 * 
	 * @Description: 根据仓单ID查询仓单信息
	 * @Author: fanyuna
	 * @Date: 2015年4月15日
	 * @param wlId
	 * @return
	 */
	public BusiWhlist selectWhlistByWlId(String wlId){
		return busiWhlistDao.selectWhlistByWlId(wlId);
	}
	
	@Override
	public void addBusiWhlistLog(BusiWhlist busiWhlist,Long userId,BusinessLogEnum logType,Object...data){
		busiWhlistLogDao.insertSelective(new BusiWhlistLog()
				.setValue(busiWhlist, logType, userId, data)) ;
	}
	
	
	private Map<String, List<BusiQualityItem>>  getGroupItem(List<BusiQualityItem> items){
		Map<String, List<BusiQualityItem>> map = new LinkedHashMap<String, List<BusiQualityItem>>();
		if(CollectionUtils.isNotEmpty(items)){
			for(int i=0;i<items.size();i++){
				BusiQualityItem bqi = items.get(i);
				String itemType = bqi.getQualityItemType();
				if(map.containsKey(itemType)){
					map.get(itemType).add(bqi);
				}else{
					List<BusiQualityItem> temp = new ArrayList<BusiQualityItem>();
					temp.add(bqi);
					map.put(itemType, temp);
				}
			}
		}
		return map;
	}

	@Override
	public int addBusiWhlistLog(BusiWhlist busiWhlist, String remark,
			Long operatorId, String optype, boolean... recordSnapshot) {
		return busiWhlistLogDao.insertBusiWhlistLog(busiWhlist, remark, operatorId, optype, recordSnapshot);
	}
}