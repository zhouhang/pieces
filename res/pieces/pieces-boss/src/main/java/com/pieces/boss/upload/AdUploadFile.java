package com.pieces.boss.upload;

import com.pieces.tools.bean.FileBo;
import com.pieces.tools.upload.AbstractUploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Created by wangbin on 2016/8/5.
 */
@Service
public class AdUploadFile extends AbstractUploadFile{


    public Map<String,Object> uploadImg(String originalFileName, InputStream inputStream){
        FileBo fileBo =  uploadFile(originalFileName,inputStream);
        Map<String,Object> map = new HashMap<>();
        map.put("url",getUrl()+fileBo.getPath());
        map.put("path",fileBo.getPath());
        return map;
    }


    @Override
    public String customImageName(String fileName) {
        Calendar now = Calendar.getInstance();
        int year=now.get(Calendar.YEAR);
        int month=now.get(Calendar.MONTH)+1;
        StringBuffer sb = new StringBuffer();
        sb.append("ads").append("/").append(year).append("/").append(month).append("/").append(UUID.randomUUID() + fileName);
        return sb.toString();
    }

}
