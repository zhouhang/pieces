package com.pieces.dao.enums;

/**
 * Created by xiao on 2016/11/16.
 */
public enum CertifyTypeEnum {

    //old:1单体药店,2连锁药店,3公立医院,4民营医院,5个体诊所,6社区医疗机构
    //new:1.药店，2.医疗机构，3.制药企业,4医药公司

    DRUGSTORE(1, "药店"),
    MEDICAL_ORG(2, "医疗机构"),
    PHARMACY(3, "制药企业"),
    MEDICINE_COMPANY(4, "医药公司");

    private Integer value;

    private String text;


    CertifyTypeEnum(Integer value, String text) {
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
        for (CertifyTypeEnum certifyTypeEnum : CertifyTypeEnum.values()) {
            if (certifyTypeEnum .getValue().equals(id)) {
                return certifyTypeEnum .getText();
            }
        }
        return null;
    }


}
