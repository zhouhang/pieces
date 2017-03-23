package com.pieces.boss.job;

import com.pieces.dao.vo.AccountBillVo;
import com.pieces.service.AccountBillService;
import com.pieces.service.impl.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Author: koabs
 * 3/1/17.
 */
@Configuration
@EnableScheduling
public class TipsJob {

    @Autowired
    AccountBillService accountBillService;

    @Autowired
    SmsService smsService;

    @Scheduled(cron = "0 0 12 ? * *")
    public void execute(){
        // 查询所有未支付的订单 并且到期时间距离今天1天 或者7天 给这些账单的用户发送短信
        List<AccountBillVo> list = accountBillService.findUnpaidBill();
        for (AccountBillVo vo: list) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            smsService.sendTipsAccoountBill(vo.getContactMobile(),vo.getCode(),format.format(vo.getRepayTime()));
            //TODO: 给跟单员发送提醒
        }
       //
    }

    // 0 0 12 ? * *   每天12点发送短信
}
