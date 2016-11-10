package com.ms.dao.enums;

/**
 * Created by xiao on 2016/11/9.
 */
public enum BizSampleEnum {

    SAMPLE_DELETED(-1,"废弃"),
    SAMPLE_NOTHANDLE(0,"寄样单已提交，等待客服来电"),
    SAMPLE_AGREE(1,"寄样单申请通过"),
    SAMPLE_REFUSE(2,"寄样单失效"),
    SAMPLE_VISTE(3,"来访验货"),
    SAMPLE_SEND(4,"已寄样"),
    SAMPLE_FINISH(5,"寄样完成");



    private BizSampleEnum(Integer value, String text) {
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
        for (BizSampleEnum sampleEnum: BizSampleEnum.values()) {
            if (sampleEnum.getValue().equals(id)) {
                return sampleEnum.getText();
            }
        }
        return null;
    }
}
