package com.yinuo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inuol.dto.user.MemberUlabelTagDto;
import com.yinuo.common.common.HttpResult;
import com.yinuo.user.service.MemberUlabelService;

/**
 * 用户的标签维护
 * 
 * @author weiss
 *
 */
@RestController
@RequestMapping("/member_ulabel")
public class MemberUlabelController {

	@Autowired
	private MemberUlabelService memberUlabelService;

	@PostMapping("/tag")
	public HttpResult<String> tag(@RequestBody MemberUlabelTagDto dto) {
		
		memberUlabelService.tag(dto);
		return HttpResult.success();
	}
}
