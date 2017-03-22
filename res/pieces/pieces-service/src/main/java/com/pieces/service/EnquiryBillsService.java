package com.pieces.service;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.EnquiryBills;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.EnquiryBillsVo;
import com.pieces.dao.vo.EnquiryRecordVo;
import com.pieces.service.constant.bean.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

/**
 * Created by wangbin on 2016/7/21.
 */
public interface EnquiryBillsService extends ICommonService<EnquiryBills>{

    void update(List<EnquiryCommoditys> enquiryCommoditysList, User user,int billId);

    Integer create(List<EnquiryCommoditys> enquiryCommoditysList, User user);

    PageInfo<EnquiryBillsVo> findByPage(int pageNum, int pageSize, EnquiryRecordVo enquiryRecordVo);


    /**
     * 根据参数查询询价列表
     * @param enquiryBillsVO
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<EnquiryBillsVo> findByParam (EnquiryBillsVo enquiryBillsVO, Integer pageNum, Integer pageSize);

    EnquiryBillsVo findVOById(Integer id);

    /**
     * 根据询价单号查找
     * @param code
     * @return
     */
    EnquiryBillsVo findVoByCode(String code);

    /**
     * 导入报价文件
     * @param file
     * @return
     */
    EnquiryBillsVo importEnquiryExcel(MultipartFile file, Integer id);

    /**
     * 下载报价excel
     */
    void exportEnquiryExcel(HttpServletResponse response, HttpServletRequest request, Integer id);

    /**
     * 根据报价商品ids下载商品报价信息
     * @param response
     * @param request
     * @param ids
     */
    void exportEnquiryExcel(HttpServletResponse response, HttpServletRequest request, String ids);

    Integer getNotHandleCount();

    List<Integer> getNotHandleIds();

    /**
     * 设置询价单为已读
     * @param id
     */
    void read(Integer id);

    /**
     * 统计当天新增的询价单
     */
    Integer countNewEnquiryBill() throws ParseException;
}
