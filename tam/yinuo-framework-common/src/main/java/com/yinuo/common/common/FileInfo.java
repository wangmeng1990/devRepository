package com.yinuo.common.common;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("文件上传返回信息")
public class FileInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "文件路径：上传文件的访问路径")
	private String path;
	@ApiModelProperty(value = "图片压缩文件路径：文件为图片时有值")
	private String compressImg;
	@ApiModelProperty(value = "视频截图：文件为视频时有值")
	private String img;
}
