package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.EnquiryBillsDao;
import com.pieces.dao.EnquiryCommoditysDao;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.EnquiryBillsVo;
import com.pieces.dao.vo.EnquiryRecordVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.EnquiryBillsService;
import com.pieces.service.EnquiryCommoditysService;
import com.pieces.service.utils.ExcelParse;
import com.pieces.tools.utils.SeqNoUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangbin on 2016/7/21.
 */
@Service
public class EnquiryBillsServiceImpl extends AbsCommonService<EnquiryBills> implements EnquiryBillsService{

    @Autowired
    private EnquiryBillsDao enquiryBillsDao;

    @Autowired
    private EnquiryCommoditysDao enquiryCommoditysDao;

    @Autowired
    private EnquiryCommoditysService enquiryCommoditysService;


    @Override
    public ICommonDao<EnquiryBills> getDao() {
        return enquiryBillsDao;
    }


    @Override
    @Transactional
    public void update(List<EnquiryCommoditys> enquiryCommoditysList, User user, int billId) {
        EnquiryBills enquiryBills = findById(billId);
        if(enquiryBills.getStatus().equals(1)){
            throw new RuntimeException("该询价单已报价，无法重新报价!");
        }
        enquiryCommoditysDao.deleteByBillId(billId);
        enquiryBills.setCreateTime(new Date());
        enquiryBillsDao.update(enquiryBills);
        //创建报价单商品
        createCommoditys(enquiryCommoditysList,user.getId(),billId);
    }

    @Override
    @Transactional
    public Integer create(List<EnquiryCommoditys> enquiryCommoditysList, User user) {
        EnquiryBills enquiryBills = new EnquiryBills();
        enquiryBills.setUserId(user.getId());
        enquiryBills.setCreateTime(new Date());
        enquiryBills.setStatus(0);
        enquiryBillsDao.create(enquiryBills);
        String code = SeqNoUtil.getEnquiryCode(enquiryBills.getId(),5);
        enquiryBills.setCode(code);
        enquiryBillsDao.update(enquiryBills);


        //创建报价单商品
        createCommoditys(enquiryCommoditysList,user.getId(),enquiryBills.getId());

        return enquiryBills.getId();
    }

    @Override
    public PageInfo<EnquiryBillsVo> findByParam(EnquiryBillsVo enquiryBillsVO, Integer pageNum, Integer pageSize) {
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<EnquiryBillsVo> list = enquiryBillsDao.findByParam(enquiryBillsVO);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public EnquiryBillsVo findVOById(Integer id) {
        EnquiryBillsVo vo = enquiryBillsDao.findVOById(id);
        vo.setEnquiryCommoditys(enquiryCommoditysDao.findByBillId(id,null, null));
        return vo;
    }

    @Override
    public EnquiryBillsVo findVoByCode(String code) {
        EnquiryBillsVo vo = new EnquiryBillsVo();
        vo.setCode(code);
        List<EnquiryBillsVo> list = enquiryBillsDao.findByParam(vo);
        vo = list.get(0);
        vo.setEnquiryCommoditys(enquiryCommoditysDao.findByBillId(vo.getId(),null, null));
        return vo;
    }

    @Override
    public PageInfo<EnquiryBillsVo> findByPage(int pageNum, int pageSize,EnquiryRecordVo enquiryRecordVo) {
        PageHelper.startPage(pageNum, pageSize);
        if (enquiryRecordVo!= null && "2".equalsIgnoreCase(String.valueOf(enquiryRecordVo.getStatus()))){
            enquiryRecordVo.setStatus(null);
            enquiryRecordVo.setExpireDate(new Date());
        }
        if (enquiryRecordVo!= null && "1".equalsIgnoreCase(String.valueOf(enquiryRecordVo.getStatus()))){
            enquiryRecordVo.setExpireDate(new Date());
        }

        List<EnquiryBillsVo> list = enquiryBillsDao.queryByParam(enquiryRecordVo);
        for (EnquiryBills enquiryBills : list) {
            List<EnquiryCommoditys> enquiryCommoditysList = enquiryCommoditysDao.findByBillId(enquiryBills.getId(),null, null);
            enquiryBills.setEnquiryCommoditys(enquiryCommoditysList);
        }
        PageInfo page = new PageInfo(list);
        return page;
    }


    private void createCommoditys(List<EnquiryCommoditys> enquiryCommoditysList, Integer userId, Integer billId){
        for(EnquiryCommoditys enquiryCommoditys : enquiryCommoditysList){
            enquiryCommoditys.setBillsId(billId);
            enquiryCommoditys.setUserId(userId);
            enquiryCommoditys.setCreateTime(new Date());
        }
        enquiryCommoditysService.create(enquiryCommoditysList);

    }

    @Override
    public  EnquiryBillsVo importEnquiryExcel(MultipartFile file,Integer id) {
        Map<Integer,EnquiryCommoditys> map = null;
        try {
            map = ExcelParse.importEnquiryInfo(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        EnquiryBillsVo vo = findVOById(id);
        for (EnquiryCommoditys commoditys : vo.getEnquiryCommoditys()) {
            EnquiryCommoditys parse = map.get(commoditys.getId());
            if (parse != null){
                commoditys.setExpireDate(parse.getExpireDate());
                commoditys.setMyPrice(parse.getMyPrice());
            }
        }

        return vo;
    }

    @Override
    public void exportEnquiryExcel(HttpServletResponse response, HttpServletRequest request, Integer id) {
        EnquiryBillsVo vo = findVOById(id);
        Workbook workbook = ExcelParse.exportEnquiryInfo(vo.getEnquiryCommoditys());
        ExcelParse.returnExcel(response,request, workbook,"报价表"+ id);
    }

    @Override
    public void exportEnquiryExcel(HttpServletResponse response, HttpServletRequest request, String ids) {
        List<EnquiryCommoditys>list = enquiryCommoditysService.findByIds(ids);
        Workbook workbook = ExcelParse.exportEnquiryInfo(list);
        ExcelParse.returnExcel(response,request, workbook,"报价表");
    }

    @Override
    public Integer getNotHandleCount() {
        return enquiryBillsDao.getNotHandleCount();
    }

    @Override
    public List<Integer> getNotHandleIds() {
        return enquiryBillsDao.getNotHandleIds();
    }

    @Override
    @Transactional
    public void read(Integer id) {
        EnquiryBills enquiryBills = new EnquiryBills();
        enquiryBills.setId(id);
        enquiryBills.setType(0);
        update(enquiryBills);
    }
}
