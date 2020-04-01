package com.yinuo.api.upload;

import java.io.File;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;



@FeignClient(value = "yinuo-upload")
public interface UploadApi {
	/***
	 * 文件下载
	 * @param filePath-fastdfs上文件路径，例：group1/M00/00/01/wKhkv14e5P2AN0YPABePLprHeuE795.mp4
	 * @return
	 */
	@PostMapping("/downFile")
	public byte[] downFile(String filePath);
	/***
	 * 文件上传
	 * @param file
	 * @return  group1/M00/00/01/wKhkv14e5P2AN0YPABePLprHeuE795.mp4
	 */
	@PostMapping("/uploadFile")
	public String uploadFile(File file);
}
