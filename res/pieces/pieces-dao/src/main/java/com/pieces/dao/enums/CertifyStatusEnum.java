package com.pieces.dao.enums;

/**
 * Created by xiao on 2016/11/16.
 */
public enum CertifyStatusEnum {

    //0未认证,1审核中，2审核通过,3审核失败

    NOT_CERTIFY(0, "未认证"),
    CERTIFY_FAIL(3, "审核失败");

    private Integer value;

    private String text;


    CertifyStatusEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * 通过ID来查询属性名称
     *
     * @param id
     * @return
     */
    public static String findByValue(Integer id) {
        for (CertifyStatusEnum certifyStatusEnum : CertifyStatusEnum.values()) {
            if (certifyStatusEnum.getValue().equals(id)) {
                return certifyStatusEnum.getText();
            }
        }
        return null;
    }


}
