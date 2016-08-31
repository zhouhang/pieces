package com.pieces.dao.vo;

import com.pieces.dao.model.PayAccount;
import com.pieces.dao.model.User;
import com.pieces.tools.utils.Reflection;

public class PayAccountVo extends PayAccount{
    /**
     * 将对象序列化为url参数.
     * @return
     */
    public String serialize(){
        String param = "";
        param = Reflection.serialize(this);
        return param;
    }
}