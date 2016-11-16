package com.pieces.dao.enums;

/**
 * Created by xiao on 2016/11/16.
 */
public enum CertifyTypeEnum {

    //1单体药店,2连锁药店,3公立医院,4民营医院,5个体诊所,6社区医疗机构

    SINGLE_DRUGSTORE(1, "单体药店"),
    CHAIN_DRUGSTORE(2, "连锁药店"),
    PUBLIC_HOSPITAL(3, "公立医院"),
    PRIVATE_HOSPITAL(4, "民营医院"),
    PRIVATE_CLINIC(5, "个体诊所"),
    COMMUNITY_MEDICINE(6, "社区医疗机构");

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
