package com.jointown.zy.common.util.image;

import java.awt.Color;
import java.io.File;

/**
 * 图片缩小类
 * 根据环境情况选择java图片缩小方式
 */
public class ImageScaleImpl implements ImageScale {
	/**
	 * 缩放图片
	 */
	public void resizeFix(File srcFile, File destFile, int boxWidth,int boxHeight) throws Exception {
		AverageImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight);
	}

	/**
	 * 裁剪图片
	 */
	@Override
	public void resizeFix(File srcFile, File destFile, int boxWidth,
			int boxHeight, int cutTop, int cutLeft, int cutWidth, int catHeight)
			throws Exception {
			AverageImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight,
					cutTop, cutLeft, cutWidth, catHeight);
	}

	/**
	 * 添加文字水印
	 */
	@Override
	public void imageMark(File srcFile, File destFile, int minWidth,
			int minHeight, int pos, int offsetX, int offsetY, String text,
			Color color, int size, int alpha) throws Exception {
			AverageImageScale.imageMark(srcFile, destFile, minWidth, minHeight,
					pos, offsetX, offsetY, text, color, size, alpha);
	}

	/**
	 * 添加logo水印
	 */
	@Override
	public void imageMark(File srcFile, File destFile, int minWidth,
			int minHeight, int pos, int offsetX, int offsetY, File markFile)
			throws Exception {
			AverageImageScale.imageMark(srcFile, destFile, minWidth, minHeight,
					pos, offsetX, offsetY, markFile);
	}
}
