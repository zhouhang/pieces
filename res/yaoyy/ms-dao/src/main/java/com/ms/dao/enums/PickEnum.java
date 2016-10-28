package com.ms.dao.enums;

/**
 * Created by xiao on 2016/10/28.
 */
public enum PickEnum {

    PICK_NOTHANDLE(0,"未处理"),
    PICK_HANDING(1,"处理中"),
    PICK_SEND(2,"已发货"),
    PICK_FINISH(3,"已完成");




    private PickEnum (Integer value, String text) {
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
        for (PickEnum  pickEnum: PickEnum.values()) {
            if (pickEnum.getValue().equals(id)) {
                return pickEnum.getText();
            }
        }
        return null;
    }
}
