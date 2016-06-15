package com.jointown.zy.common.dto;

import java.util.List;

import com.jointown.zy.common.model.UcUserContact;

/**
 * @ClassName: UcUserContactDto
 * @Description: 完善联系人信息dto
 * @Author: Calvin.wh
 * @Date: 2015-10-20
 * @Version: 1.0
 */
public class UcUserContactDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private List<UcUserContact> ucUserContact;

	public List<UcUserContact> getUcUserContact() {
		return ucUserContact;
	}

	public void setUcUserContact(List<UcUserContact> ucUserContact) {
		this.ucUserContact = ucUserContact;
	}

}
