package com.yinuo.upload.client;


import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yinuo.common.utils.DataUtils;

public class UploadHelper {
	private static final int GET_FRAMES_LENGTH = 5;
	/**
	 * @descrption 根据HttpServletRequest对象获取MultipartFile集合
	 * @param request
	 * @param maxLength    文件最大限制
	 * @param allowExtName 不允许上传的文件扩展名
	 * @return MultipartFile集合
	 */
	private static Logger logger = LoggerFactory.getLogger(UploadHelper.class);
	public static List<MultipartFile> getFileSet(HttpServletRequest request, long maxLength, String[] allowExtName) {
		MultipartHttpServletRequest multipartRequest = null;
		List<MultipartFile> files = new LinkedList<MultipartFile>();
		try {
			multipartRequest = (MultipartHttpServletRequest) request;
			 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			 Set<String> keys = fileMap.keySet();
			 for(String key:keys) {
				 MultipartFile file= fileMap.get(key);
				 files.add(file);
			 }
		} catch (Exception e) {
			return new LinkedList<MultipartFile>();
		}
		 return files;
	}
	
	/**
	 * @descrption 验证文件格式，这里主要验证后缀名
	 * @param file         MultipartFile对象
	 * @param maxLength    文件最大限制
	 * @param allowExtName 不允许上传的文件扩展名
	 * @return 文件格式是否合法
	 */
	public static boolean validateFile(MultipartFile file, long maxLength, String[] allowExtName) {
		if (file.getSize() < 0 || file.getSize() > maxLength)
			return false;
		String filename = file.getOriginalFilename();

		// 处理不选择文件点击上传时，也会有MultipartFile对象，在此进行过滤
		if (filename == "") {
			return false;
		}
		String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
		if (allowExtName == null || allowExtName.length == 0 || Arrays.binarySearch(allowExtName, extName) != -1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static File transFile(MultipartFile multipartFile, String fileTemp)
			throws IllegalStateException, IOException {
		String fileName = multipartFile.getOriginalFilename();
		File file = new File(fileTemp);
		if (!file.exists()) {
			file.mkdir();
		}
		File fileTarget = new File(fileTemp + File.separatorChar + DataUtils.uuid()+"-temp-"+fileName);
		multipartFile.transferTo(fileTarget);
		return fileTarget;
	}
	
	/**
	 * 从路径中提取文件后缀名，不带句号
	 *
	 * @param fileName
	 * @return
	 */
	public static String fileExt(String fileName) {
		if (fileName == null)
			return "";

		int dot = fileName.lastIndexOf(".");
		if (dot == -1)
			return "";

		String ret = fileName.substring(dot + 1);
		for (int i = 0; i < ret.length(); i++) {
			char c = ret.charAt(i);
			boolean isAlpha = ('0' <= c && c <= '9') || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
			if (!isAlpha) {
				return "";
			}
		}
		return ret;
	}

	
	public static Map<String, Object> getScreenshot(String filePath, String saveImagePath) {
		logger.info("视频文件[{}]截图开始", filePath);

        Map<String, Object> result = new HashMap<String, Object>();
        FFmpegFrameGrabber grabber;
        try {
            grabber = FFmpegFrameGrabber.createDefault(filePath);
            // 第一帧图片存储位置(也是视频路径)
            String targerFilePath = filePath.substring(0, filePath.lastIndexOf(File.separatorChar));
            // 视频文件名
            String fileName = filePath.substring(filePath.lastIndexOf(File.separatorChar) + 1);
            // 图片名称
            String targetFileName = fileName.substring(0, fileName.lastIndexOf("."));
            grabber.start();
            // 视频总帧数
            int videoLength = grabber.getLengthInFrames();

            Frame frame = null;
            int i = 0;
            while (i < videoLength) {
                // 过滤前5帧,避免出现全黑的图片,依自己情况而定(每循环一次取一帧)
                frame = grabber.grabFrame();
                if ((i > GET_FRAMES_LENGTH) && (frame.image != null)) {
                    break;
                }
                i++;
            }

            // 视频旋转度
            String rotate = grabber.getVideoMetadata("rotate");
            Java2DFrameConverter converter = new Java2DFrameConverter();
            // 绘制图片
            BufferedImage bi = converter.getBufferedImage(frame);
            if (rotate != null) {
                // 旋转图片
                bi = rotate(bi, Integer.parseInt(rotate));
            }
            // 图片的类型
            String imageMat = "jpg";
            // 图片的完整路径
            String imagePath = targerFilePath + File.separator +DataUtils.uuid()+"-shot-"+ targetFileName + "." + imageMat;
            if (null != saveImagePath && !"".equals(saveImagePath)) {
                // 指定路径
                imagePath = saveImagePath + "." + imageMat;
            }

            // 创建文件
            File output = new File(imagePath);
            ImageIO.write(bi, imageMat, output);
            // 拼接Map信息
//            result.put("videoLength", videoLength); // 视频总帧数
//            result.put("videoWide", bi.getWidth()); // 视频的宽
//            result.put("videoHigh", bi.getHeight());// 频的高
//            long duration = grabber.getLengthInTime() / (1000 * 1000); // 此视频时长(s/秒)
//            result.put("rotate", (null == rotate || "".equals(rotate))? "0" : rotate); // 视频的旋转度
//            result.put("format", grabber.getFormat()); // 视频的格式
//            result.put("duration", duration);
            result.put("imgPath", output.getPath());
            grabber.stop();
            logger.info("视频文件[{}]截图结束,图片地址为[{}]", filePath, imagePath);
        } catch (IOException e) {
        	logger.error("视频信息帧数处理发生异常 [{}]", e.getMessage());
            e.printStackTrace();
        }

        return result;
    }


    /**
     * <h5>功能:根据视频旋转度来调整图片</h5>
     * 
     * @param src 捕获的图像
     * @param angel 视频旋转度
     * @return BufferedImage
     */
    private static BufferedImage rotate(BufferedImage src, int angel) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        int type = src.getColorModel().getTransparency();
        Rectangle rect_des = calcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);
        BufferedImage bi = new BufferedImage(rect_des.width, rect_des.height, type);
        Graphics2D g2 = bi.createGraphics();
        g2.translate((rect_des.width - src_width) / 2, (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return bi;
    }

    /**
     * <h5>功能:计算图片旋转大小</h5>
     * 
     * @param src 屏幕坐标中捕获的矩形区域
     * @param angel 视频旋转度
     * @return 
     */
    private static Rectangle calcRotatedSize(Rectangle src, int angel) {
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new java.awt.Rectangle(new Dimension(des_width, des_height));
    }
}
