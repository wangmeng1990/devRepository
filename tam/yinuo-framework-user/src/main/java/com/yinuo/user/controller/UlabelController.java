package com.yinuo.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inuol.user.Ulabel;
import com.yinuo.user.service.UlabelService;

/**
 * 用户标签controller
 * 
 * @author weiss
 *
 */
@RestController
@RequestMapping("/ulabel")
public class UlabelController {

	@Autowired
	private UlabelService ulabelService;

	@GetMapping("/list")
	public List<Ulabel> list() {

		List<Ulabel> list = ulabelService.queryAllUlabels();
		return list;
	}
}
