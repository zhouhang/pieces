package com.ms.dao.enums;

/**
 * Created by xiao on 2016/10/20.
 */
public enum  SampleEnum {

    SAMPLE_DELETED(-1,"废弃"),
    SAMPLE_NOTHANDLE(0,"未受理"),
    SAMPLE_AGREE(1,"同意寄样"),
    SAMPLE_REFUSE(2,"拒绝寄样"),
    SAMPLE_VISTE(3,"客户来访"),
    SAMPLE_SEND(4,"已寄样"),
    SAMPLE_FINISH(5,"寄样完成");



    private SampleEnum(Integer value, String text) {
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
        for (SampleEnum sampleEnum: SampleEnum.values()) {
            if (sampleEnum.getValue().equals(id)) {
                return sampleEnum.getText();
            }
        }
        return null;
    }

}
