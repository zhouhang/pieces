package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.PayAccountDao;
import com.pieces.dao.model.PayAccount;
import com.pieces.dao.vo.PayAccountVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.PayAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PayAccountServiceImpl  extends AbsCommonService<PayAccount> implements PayAccountService{

	@Autowired
	private PayAccountDao payAccountDao;


	@Override
	public PageInfo<PayAccountVo> findByParams(PayAccountVo payAccountVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<PayAccountVo>  list = payAccountDao.findByParams(payAccountVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	public ICommonDao<PayAccount> getDao() {
		return payAccountDao;
	}

}
