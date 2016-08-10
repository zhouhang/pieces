package com.pieces.tools.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author: koabs
 * 8/10/16.
 */
@Component
public interface IUploadConfig {


    public String getAbsolutePath();

    public String getUrlPre();

}
