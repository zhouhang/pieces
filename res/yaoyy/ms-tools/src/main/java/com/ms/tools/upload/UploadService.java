package com.ms.tools.upload;

import com.ms.tools.ueditor.CropInfo;
import com.ms.tools.ueditor.CropResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author: koabs
 * 10/20/16.
 * 文件上传Service
 */
public interface UploadService {
    /**
     * 上传文件
     * @return
     */
    public CropResult uploadImage(MultipartFile img);

    /**
     * 富文本编辑器上传图片
     * @param img
     * @return
     */
    public CropResult uploadUeditorImage(MultipartFile img);

    /**
     * 裁剪图片
     * @param crop
     * @return
     */
    public CropResult cropImg(CropInfo crop);
}
