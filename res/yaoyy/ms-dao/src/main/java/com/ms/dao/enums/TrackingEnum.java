package com.ms.dao.enums;

/**
 * Created by xiao on 2016/10/20.
 */
public enum TrackingEnum {

    TRACKING_APPLY(0,"申请寄样"),
    TRACKING_AGREE(1,"同意寄样"),
    TRACKING_REFUSE(2,"拒绝寄样"),
    TRACKING_SEND(3,"寄送样品"),
    TRACKING_GET(4,"客户已收货"),
    TRACKING_ORDER(5,"客户预约"),
    TRACKING_RECORD(6,"跟踪记录"),
    TRACKING_MESSAGE(7,"用户留言"),
    TRACKING_FINISH(8,"该寄样单受理完成");







    private TrackingEnum(Integer value, String text) {
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
        for (TrackingEnum trackingEnum: TrackingEnum.values()) {
            if (trackingEnum.getValue().equals(id)) {
                return trackingEnum.getText();
            }
        }
        return null;
    }



}
