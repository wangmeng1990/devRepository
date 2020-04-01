package com.inuol.dto;

import lombok.Data;

@Data
public class AppointmentDto extends BaseRequestDto{
	private String mobile;
	private String name; 
	private String serialNo; 
	private String createDateStart; 
	private String createDateEnd; 
	private String playDate; 
	private String status; 
}
