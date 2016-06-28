///**
// * @Description: 
// *
// * @Title: QuartzTest.java
// * @Package com.joyce.quartz.main
// * @Copyright: Copyright (c) 2014
// *
// * @author Comsys-LZP
// * @date 2014-6-26 下午03:35:05
// * @version V2.0
// */
//package org.jzt.task;
//
//import java.util.HashMap;
//import java.util.List;
//
//import com.jointown.zy.common.service.impl.BusiListingServiceImpl;
//import com.jointown.zy.common.service.impl.BusiWhlistServiceImpl;
//import com.jointown.zy.common.util.TimeUtil;
//import com.jointown.zy.common.vo.BusiListingDetailVo;
//import com.jointown.zy.web.task.SpringBeanFactroy;
//
//
///**
// * @Description: 测试类
// *
// * @ClassName: QuartzTest
// * @Copyright: Copyright (c) 2014
// *
// * @author Comsys-LZP
// * @date 2014-6-26 下午03:35:05
// * @version V2.0
// */
//public class QuartzTest {
//	public static void main(String[] args) {
//		BusiListingServiceImpl busiListingService =(BusiListingServiceImpl)SpringBeanFactroy.getBean(BusiListingServiceImpl.class);
//		BusiWhlistServiceImpl busiWhlistService=(BusiWhlistServiceImpl)SpringBeanFactroy.getBean(BusiWhlistServiceImpl.class);
//		
//			List<BusiListingDetailVo> list = busiListingService.findListings();
//			for(BusiListingDetailVo vo :list){
//				if(TimeUtil.compare(vo.getExpiretime(),vo.getSysdate())<0){
//					String x=String.valueOf(vo.getWlsurplus());
//					String wlid=vo.getWlid();
//					String listingid=vo.getListingid();
//					int num= busiListingService.updateListingState(listingid);
//					if(num >0){
//						HashMap<String, Object> map = new HashMap<String, Object>();
//						map.put("wlsurplus", x);
//						map.put("wlid", wlid);
//						busiWhlistService.updateWLsurplus(map);
//					}
//				}
//			}
//		
//		
//		
////		try {
////			String job_name = "动态任务调度";
////			System.out.println("【系统启动】开始(每1秒输出一次)...");  
////			QuartzManager.addJob(job_name, QuartzJob.class, "0/1 * * * * ?");  
////			
////			Thread.sleep(5000);  
////			System.out.println("【修改时间】开始(每2秒输出一次)...");  
////			QuartzManager.modifyJobTime(job_name, "10/2 * * * * ?");  
////			Thread.sleep(6000);  
////			System.out.println("【移除定时】开始...");  
////			QuartzManager.removeJob(job_name);  
////			System.out.println("【移除定时】成功");  
////			
////			System.out.println("【再次添加定时任务】开始(每10秒输出一次)...");  
////			QuartzManager.addJob(job_name, QuartzJob.class, "*/10 * * * * ?");  
////			Thread.sleep(60000);  
////			System.out.println("【移除定时】开始...");  
////			QuartzManager.removeJob(job_name);  
////			System.out.println("【移除定时】成功");
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//	}
//}
