/**
 * @author guoyb
 * 2015年3月12日 下午3:00:01
 */
package com.jointown.zy.common.vo;

/**
 * @author guoyb
 * 2015年3月12日 下午3:00:01
 */
public class EastYcPriceHistoryVo {
		//月份
		private String ttm;

		//价格
		private String price;
		
		//规格
		private String guige;
		
		//产地
		private String chandi;
		
		//药材名
		private String ycnam;

		public String getTtm() {
			return ttm;
		}

		public void setTtm(String ttm) {
			this.ttm = ttm;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getGuige() {
			return guige;
		}

		public void setGuige(String guige) {
			this.guige = guige;
		}

		public String getChandi() {
			return chandi;
		}

		public void setChandi(String chandi) {
			this.chandi = chandi;
		}

		public String getYcnam() {
			return ycnam;
		}

		public void setYcnam(String ycnam) {
			this.ycnam = ycnam;
		}
}
