package com.ms.dao.enums;

/**
 * Created by xiao on 2016/10/31.
 */
public enum PickTrackingTypeEnum {


    PICK_APPLY(0,"提交选货单"),
    PICK_AGREE(1,"同意受理选货单"),
    TPICK_REFUSE(2,"拒绝受理选货单"),
    PICK_RECORD(3,"跟踪记录"),
    PICK_NOT_FINISH(4,"该选货单未完成"),
    PICK_FINISH(5,"该选货单交易完成");







    private PickTrackingTypeEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    private Integer value;
    private String text;
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
        for (PickTrackingTypeEnum trackingEnum: PickTrackingTypeEnum.values()) {
            if (trackingEnum.getValue().equals(id)) {
                return trackingEnum.getText();
            }
        }
        return null;
    }

}
