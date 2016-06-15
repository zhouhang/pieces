package com.jointown.zy.common.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("restriction")
public class ImageHelper {
	// 水印透明度 
    private static float alpha = 0.3f;
    // 水印文字字体
    private static Font font_XXXL = new Font("宋体", Font.BOLD, 200);
    
    private static Font font_XXL = new Font("宋体", Font.BOLD, 120);
    
    private static Font font_XL = new Font("宋体", Font.BOLD, 80);
    
    private static Font font_L = new Font("宋体", Font.BOLD, 60);
    
    private static Font font_M = new Font("宋体", Font.BOLD, 40);
    
    private static Font font_S = new Font("宋体", Font.BOLD, 23);
    
    private static Font font_SS = new Font("宋体", Font.BOLD, 10);
    // 水印文字颜色
    private static Color color = Color.red;
	/** 
     * 根据缩放后图片的宽和高计算缩放的比例 
     * @param source  图片的原始存放路径 
     * @param w  缩放后的图片的宽度 
     * @param h  缩放后的图片的高度 
     * @return 返回缩放后的图片的缓存 
     */  
    public static Map<String,Object> equalScale(InputStream imgInputStream, int picWidth, int picHeight){
    	Map<String,Object> map = new HashMap<String,Object>();
        try {  
            BufferedImage buffer = ImageIO.read(imgInputStream);
            map.put("buffer", buffer);
            int width = buffer.getWidth(null);  //图片原宽度
            int height = buffer.getHeight(null);  //图片原高度
            int newwidth = 0;// 最终宽度
			int newheight = 0;// 最终高度
			//逻辑判断
			if (width>height) {// 宽大于高。
				if (width > picWidth) {
					int _temp_height = (height * picWidth / width);
					newwidth=picWidth;
					newheight=_temp_height;
					if(_temp_height>picHeight){
						int _temp_width = (picWidth * picHeight / _temp_height);
						newwidth=_temp_width;
						newheight=picHeight;
					}
				} else {
					newwidth=width;
					newheight=height;
				}
			} else {
				if (height > picHeight) {
					int _temp_width = (width * picHeight / height);
					newwidth=_temp_width;
					newheight=picHeight;
					if(_temp_width>picWidth){
						int _temp_height = (height * picWidth / picWidth);
						newwidth=picWidth;
						newheight=_temp_height;
					}
				} else {
					newwidth=width;
					newheight=height;
				}
			}
            map.put("width", newwidth) ;
            map.put("height", newheight);
            return map;
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
	
	public   static   void  scaleImage(InputStream imgInputStream,OutputStream imgOutputStream,  int  w,int h)
    {
         try 
        {
        	 Map<String,Object> map = equalScale(imgInputStream,w,h);
        	 int width = map.get("width")==null?w:Integer.valueOf(map.get("width").toString());
        	 int height = map.get("height")==null?h:Integer.valueOf(map.get("height").toString());
        	 Image src  = (BufferedImage) map.get("buffer");
        	 BufferedImage bufferedImage  =   new  BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

     		// Soften.
			float softenFactor = 0.05f;
			float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
			Kernel kernel = new Kernel(3, 3, softenArray);
			ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
			bufferedImage = cOp.filter(bufferedImage, null);
				
            bufferedImage.getGraphics().drawImage(
                    src.getScaledInstance(width, height, Image.SCALE_SMOOTH),
                     0 ,  0 ,  null );
            
            // Encodes image as a JPEG data stream
            JPEGImageEncoder encoder  =  JPEGCodec.createJPEGEncoder(imgOutputStream);
            
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
			param.setQuality(1.0f, true);

			encoder.setJPEGEncodeParam(param);
            encoder.encode(bufferedImage);
        }
         catch  (IOException e)
        {
            e.printStackTrace();
        }
         finally{
        	if(imgInputStream !=null)
				try {
					imgInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	if(imgOutputStream !=null)
				try {
					imgOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
         }
    }
	
	public   static  InputStream  scaleImage(InputStream imgInputStream,  int  w,int h)
    {
		InputStream is = null;
         try 
        {
        	 Map<String,Object> map = equalScale(imgInputStream,w,h);
        	 int width = map.get("width")==null?w:Integer.valueOf(map.get("width").toString());
        	 int height = map.get("height")==null?h:Integer.valueOf(map.get("height").toString());
        	 Image src  = (BufferedImage) map.get("buffer");
        	 BufferedImage bufferedImage  =   new  BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

     		// Soften.
			float softenFactor = 0.05f;
			float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
			Kernel kernel = new Kernel(3, 3, softenArray);
			ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
			bufferedImage = cOp.filter(bufferedImage, null);
				
            bufferedImage.getGraphics().drawImage(
                    src.getScaledInstance(width, height, Image.SCALE_SMOOTH),
                     0 ,  0 ,  null );
            
            bufferedImage.flush();
            
            ByteArrayOutputStream bs = new ByteArrayOutputStream();  
            ImageOutputStream imOut = ImageIO.createImageOutputStream(bs); 
            ImageIO.write(bufferedImage, "jpg",imOut); 
            
            // Encodes image as a JPEG data stream
            JPEGImageEncoder encoder  =  JPEGCodec.createJPEGEncoder(bs);
            
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
			param.setQuality(1.0f, true);

			encoder.setJPEGEncodeParam(param);
            encoder.encode(bufferedImage);
                 
            is= new ByteArrayInputStream(bs.toByteArray()); 
        }
         catch  (IOException e)
        {
            e.printStackTrace();
        }
         finally{
        	if(imgInputStream !=null)
				try {
					imgInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
         }
		return is;
    }
	
	public   static   void  scaleImage(String imgSrc, String imgDist,  int  w,int h)
    {
         try 
        {
            File file  =   new  File(imgSrc);
             if  ( ! file.exists())
            {
                 return ;
            }
            InputStream is  =   new  FileInputStream(file);
            OutputStream os  =   new  FileOutputStream(imgDist);
            scaleImage(is, os, w,h);
        }
         catch  (Exception e)
        {

        }
    }
	
    
    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度
     * 
     * @param logoText
     * @param srcImgPath
     * @param targerPath
     * @param degree
     */
    public static void markImageByText(String logoText, String filePath) {
         
        InputStream is = null;
        OutputStream os = null;
        File file =null;
        try {
        	file=new File(filePath);
        	Image srcImg =ImageIO.read(file);
            // 1、源图片
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
 
            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            // 4、设置水印旋转
            int width=srcImg.getWidth(null);//原图宽度
            int height=srcImg.getHeight(null);//原图高度
            int degree=370;
            //动态获取角度
            BigDecimal tanN=new BigDecimal(height).divide(new BigDecimal(width), 2, BigDecimal.ROUND_FLOOR);//角度N的tan值
            if(tanN.compareTo(new BigDecimal(0.26))<0){ //15°以下
            	degree=degree-15;
            }else if(tanN.compareTo(new BigDecimal(0.57))<0){//30°以下
            	degree=degree-30;
            }else if(tanN.compareTo(new BigDecimal(1))<=0){//45°以下
            	degree=degree-45;
            }else if(tanN.compareTo(new BigDecimal(1.73))<0){//60°以下
            	degree=degree-60;
            }else if(tanN.compareTo(new BigDecimal(3.73))<0){//75°以下
            	degree=degree-75;
            }else{
            	degree=degree-85;
            }
            g.rotate(Math.toRadians(degree),(double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
           
            //动态设置大小
            if(height<150 || width<150){
            	g.setFont(font_SS);
            }else if(height<250 || width<250){
            	g.setFont(font_S);
            }else if(height<380 || width<380){
            	g.setFont(font_M);
            }else if(height<580 || width<580){
            	g.setFont(font_L);
            }else if(height<1000 || width<1000){
            	g.setFont(font_XL);
            }else if(height<1500 || width<1500){
            	g.setFont(font_XXL);
            }else{
            	g.setFont(font_XXXL);
            }
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            g.drawString(logoText, buffImg.getWidth() / 6, buffImg.getHeight() / 2);
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            ImageIO.write(buffImg, "JPG", file);
            
             
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
	public static void main(String[] args) {
		scaleImage("E:\\pictures\\warehouse_07.png","E:\\warehouse_07-278-278.png",278,278);
	}
}
