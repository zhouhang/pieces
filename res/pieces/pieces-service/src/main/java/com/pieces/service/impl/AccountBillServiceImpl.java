package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.AccountBillDao;
import com.pieces.dao.model.AccountBill;
import com.pieces.dao.vo.AccountBillVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.AccountBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountBillServiceImpl  extends AbsCommonService<AccountBill> implements AccountBillService{

	@Autowired
	private AccountBillDao accountBillDao;


	@Override
	public PageInfo<AccountBillVo> findByParams(AccountBillVo accountBillVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<AccountBillVo>  list = accountBillDao.findByParams(accountBillVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<AccountBill> getDao() {
		return accountBillDao;
	}


}
