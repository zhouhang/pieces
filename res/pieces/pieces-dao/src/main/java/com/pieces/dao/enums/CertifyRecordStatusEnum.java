package com.pieces.dao.enums;

/**
 * Created by xiao on 2016/11/17.
 */
public enum CertifyRecordStatusEnum {


    NOT_HANDLE(0, "未处理"),
    CERTIFY_FAIL(2, "审核不通过"),
    CERTIFY_SUCESS(1, "审核通过");

    private Integer value;

    private String text;


    CertifyRecordStatusEnum (Integer value, String text) {
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
        for (CertifyRecordStatusEnum  certifyRecordStatusEnum : CertifyRecordStatusEnum .values()) {
            if (certifyRecordStatusEnum.getValue().equals(id)) {
                return certifyRecordStatusEnum.getText();
            }
        }
        return null;
    }
}
