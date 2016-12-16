package com.pieces.dao.enums;

/**
 * Created by xiao on 2016/12/16.
 */
public enum ErpCompanyTypeEnum {

    ERP_4(4, "单体药店"),
    ERP_3(3, "连锁药店"),
    ERP_5(5, "公立医院&&民营医院"),
    ERP_11(11, "个体诊所"),
    ERP_7(7, "社区医疗机构");

    private Integer value;

    private String text;


    ErpCompanyTypeEnum(Integer value, String text) {
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

    public static Integer findByStr(String name) {
        for (ErpCompanyTypeEnum erpCompanyTypeEnum : ErpCompanyTypeEnum.values()) {
            if (erpCompanyTypeEnum .getText().indexOf(name)!=-1) {
                return erpCompanyTypeEnum.getValue();
            }
        }
        return null;
    }


}
