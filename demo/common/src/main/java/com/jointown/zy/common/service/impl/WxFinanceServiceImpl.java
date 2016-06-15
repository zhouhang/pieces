package com.jointown.zy.common.service.impl;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.WxFinanceDao;
import com.jointown.zy.common.dto.WxFinanceDto;
import com.jointown.zy.common.model.WxFinance;
import com.jointown.zy.common.service.WxFinanceService;
import com.jointown.zy.common.util.TimeUtil;

/**
 * 我要融资业务交互实现类
 * @author Mr.song
 * @date 2015-8-25
 */
@Service(value="wxFinanceService")
public class WxFinanceServiceImpl implements WxFinanceService {
	private static Logger logger = LoggerFactory.getLogger(WxFinanceServiceImpl.class);
	
	@Autowired
	private WxFinanceDao wxFinanceDao;
	
	/**
	 * 保存我要融资基本数据
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean saveFinance(WxFinanceDto wxFinanceDto) {
		logger.info("WxFinanceServiceImpl.insertSelective start");
		boolean flag =false;
		if(wxFinanceDto == null){
			logger.error("WxFinanceServiceImpl.insertSelective:融资对象不存在！");
			return flag;
		}
		try{
			WxFinance o =  new WxFinance();
			Date dd = new Date();
			o.setCreateTime(dd);
			o.setUpdateTime(dd);
			o.setFinanceBreedName(wxFinanceDto.getFinanceBreedName());
			o.setFinanceBreedStandardLevel(wxFinanceDto.getFinanceBreedStandardLevel());
			o.setFinanceBreedAmount(wxFinanceDto.getFinanceBreedAmount());
			o.setFinanceAddress(wxFinanceDto.getFinanceAddress());
			o.setFinanceDate(new Date(wxFinanceDto.getFinanceDate().replace("-", "/")));
			o.setFinanceName(wxFinanceDto.getFinanceName());
			o.setFinanceMobile(wxFinanceDto.getFinanceMobile());
			o.setStatus(Short.decode("0"));
			int i = wxFinanceDao.insertSelective(o);
			if(i > 0)flag = true;
		}catch(IllegalArgumentException excetion){
			logger.error("WxFinanceServiceImpl.insertSelective:运行错误:{}",excetion.getMessage());
		}
		return flag;
	}
}
