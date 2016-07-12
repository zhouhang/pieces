package com.pieces.dao.vo;

import java.lang.reflect.Field;

/**
 * Created by wangbin on 2016/7/8.
 */
public class MemberVo {

    private Integer id;

    private String username;

    private String name;

    private Integer roleId;

    private String email;

    private Boolean isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isdel) {
        isDel = isdel;
    }


    @Override
    public String toString() {
        Field[] fields = this.getClass().getFields();
        StringBuffer sb = new StringBuffer();
        try {
            for(Field field : fields){
                if(field.get(this)!=null){
                    sb.append("&").append(field.getName()).append("=").append(field.get(this).toString());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
