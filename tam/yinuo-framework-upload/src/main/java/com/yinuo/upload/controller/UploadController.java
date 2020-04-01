package com.yinuo.upload.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.yinuo.common.common.FileInfo;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.utils.DataUtils;
import com.yinuo.upload.client.UploadHelper;
import com.yinuo.upload.service.UploadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api("文件上传")
public class UploadController {
	@Value("${fdfsClientUrl}")
	private String fdfsClientUrl;
	@Value("${fileTemp}")
	private String fileTemp;
	@Autowired
	UploadService uploadService;
	@Resource
	private FastFileStorageClient fastFileStorageClient;
	@Resource
	private ThumbImageConfig thumbImageConfig;

	private Logger logger = LoggerFactory.getLogger(getClass());

	/***
	 * 上传文件-后台
	 * @param file
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "后台调用")
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(
			@RequestParam(required = true) File file,
			HttpServletRequest request) {
		InputStream inputStream = null;
		String path = null;
		try {
			inputStream = new FileInputStream(file);
			StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.length(), UploadHelper.fileExt(file.getName()), null);
			path=storePath.getFullPath();
		} catch (Exception e) {
			logger.error("文件上传失败："+file.getName());
			e.printStackTrace();
		}finally {
			if(null!=inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("文件溜关闭异常");
					e.printStackTrace();
				}
			}
		}
		return path;
	}

	/***
	 * 下载文件-后台
	 * @param path
	 * @param targetPath
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "后台调用")
	@RequestMapping(value = "/downFile", method = RequestMethod.POST)
	public byte[] downFile(@RequestParam(required = true) String filePath,
			@RequestParam(required = false) String targetPath, HttpServletRequest request) {
		String group = filePath.substring(0, filePath.indexOf("/"));
        String path = filePath.substring(filePath.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, downloadByteArray);
		return bytes;
	}

	
	@ApiOperation(value = "单文件上传")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public HttpResult<FileInfo> upload(
			@ApiParam(name = "file", value = "文件") @RequestParam(required = true) MultipartFile file,
			HttpServletRequest request) throws IOException {
		FileInfo info=doUpload(file);
		return HttpResult.success(info);
	}
	
	
	@ApiOperation(value = "多文件上传")
	@RequestMapping(value = "/uploadMulti",produces = "application/json; charset=UTF-8" , method = RequestMethod.POST)
	public HttpResult<List<FileInfo>> uploadMulti(
			@ApiParam(name = "files", value = "文件") @RequestParam(required = true) MultipartFile[] files,
			HttpServletRequest request) throws IOException {
		//List<MultipartFile> files = UploadHelper.getFileSet(request, 0, null);
		logger.info("files:"+files.length);
		List<FileInfo> list=new ArrayList<FileInfo>();
		for(MultipartFile f:files) {
			FileInfo info = new FileInfo();
			info=doUpload(f);
			list.add(info);
		}
		return HttpResult.success(list);
	}
	
	private FileInfo doUpload(MultipartFile file) {
		FileInfo info = new FileInfo();
		InputStream inputStream=null;
		try {
			inputStream = file.getInputStream();
			String ext = UploadHelper.fileExt(file.getOriginalFilename());
			StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.getSize(), ext, null);
			info.setPath(fdfsClientUrl+storePath.getFullPath());
			if ("mp4".equals(ext)) {
				FileInputStream fio = null;
				try {
					File tempfile = UploadHelper.transFile(file, fileTemp);
					Map<String, Object> map = UploadHelper.getScreenshot(tempfile.getPath(), null);
					if (DataUtils.isNotEmpty((String) map.get("imgPath"))) {// 把视频截图上传fastdfs
						File imgFile = new File((String) map.get("imgPath"));
						fio = new FileInputStream(imgFile);
						StorePath imgStorePath = fastFileStorageClient.uploadFile(fio,
								imgFile.length(), UploadHelper.fileExt(imgFile.getName()), null);
						info.setImg(fdfsClientUrl+imgStorePath.getFullPath());
						fio.close();//关闭流临时文件解除占用才可删除
						imgFile.delete();
					}
					tempfile.delete();
				} catch (Exception e) {
					logger.error("视频截图生成失败");
					e.printStackTrace();
				}finally {
					if(null!=fio) {
						fio.close();
					}
				}
			}else if("jpg".equals(ext)||"png".equals(ext)) {
				InputStream ysInputStream=null;
				try {
					
//					String name=fileTemp + File.separatorChar + DataUtils.uuid()+"-YS-"+file.getOriginalFilename();
//					File ysFile=new File(name);
//					ysFile.createNewFile();
//					ysFile.canRead();
//					ysFile.canWrite();
//					//Thumbnails.of(inputStream).scale(1f).outputQuality(0.25f).outputFormat(ext).toFile(name);//源文件压缩
//					
//					Thumbnails.Builder builder = Thumbnails.of(inputStream).scale(1f).outputQuality(0.25f).outputFormat("jpg");
//					// 写入到内存
//		            ByteArrayOutputStream baos = new ByteArrayOutputStream(); //字节输出流（写入到内存）
//		            builder.toOutputStream(baos);
//		            File desFile = new File(name);
//		            FileOutputStream fos = new FileOutputStream(desFile);
//		            byte[] bytes=baos.toByteArray();
//					fos.write(bytes);
//					fos.close();
//					
//					ysInputStream=new FileInputStream(ysFile);
//					StorePath ysStorePath =fastFileStorageClient.uploadFile(ysInputStream, ysFile.length(), ext, null);
					info.setCompressImg(fdfsClientUrl+storePath.getFullPath());
				} catch (Exception e) {
					logger.error("压缩图片失败");
					e.printStackTrace();
				}finally {
					if(null!=ysInputStream) {
						ysInputStream.close();
					}
				}
			}
		} catch (Exception e) {
			logger.error("文件上传失败：" + file.getOriginalFilename());
			e.printStackTrace();
			return null;
		}finally {
			if(null!=inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("inputStream关闭异常");
					e.printStackTrace();
				}
			}
		}
		return info;
	}
}
