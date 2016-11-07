package com.ms.tools.upload;

import com.ms.tools.ImageUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

/**
 * Author: koabs
 * 8/10/16.
 */
@Component
public class UEditorUploadFile extends AbstractUploadFile {

    @Override
    public String customImageName(String fileName) {
        Calendar now = Calendar.getInstance();
        int year=now.get(Calendar.YEAR);
        int month=now.get(Calendar.MONTH)+1;
        StringBuffer sb = new StringBuffer();
        sb.append("ueditor").append("/").append(year).append("/").append(month).append("/").append(UUID.randomUUID() + fileName);
        return sb.toString();
    }

    @Override
    public InputStream addWatermark(InputStream inputStream, String ext) throws IOException {
        // 加水印
        InputStream input = UEditorUploadFile.class.getClassLoader().getResourceAsStream("img/watermark.png");
        if (input != null) {
            ext = ext.substring(1);
            BufferedImage bi = ImageIO.read(inputStream);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Thumbnails.of(bi).size(bi.getWidth(), bi.getHeight()).watermark(
                    Positions.CENTER,
                    ImageIO.read(AbstractUploadFile.class.getClassLoader().getResourceAsStream("img/watermark.png")), 1.0f)
                    .outputQuality(1.0f).outputFormat(ext).toOutputStream(os);
            inputStream = new ByteArrayInputStream(os.toByteArray());
        }

        return inputStream;
    }
}
