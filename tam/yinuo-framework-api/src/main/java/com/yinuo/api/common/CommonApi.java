package com.yinuo.api.common;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "yinuo-manage")
public interface CommonApi {

	@RequestMapping(value = "/manageController/getPopList_manage",method = RequestMethod.POST)
	Object getAllUser();

}
