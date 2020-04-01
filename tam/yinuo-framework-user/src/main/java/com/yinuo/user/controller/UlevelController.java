package com.yinuo.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.inuol.user.Ulevel;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.utils.generator.UUIDHexGenerator;
import com.yinuo.user.service.UlevelService;

/**
 * 会员等级controller
 * 
 * @author weiss
 *
 */
@RestController
@RequestMapping("/ulevel")
public class UlevelController {

	@Autowired
	private UlevelService ulevelService;

	@PostMapping("/add")
	public HttpResult<String> add(@RequestBody Ulevel ulevel) {

		ulevel.setId(UUIDHexGenerator.generate());
		ulevelService.addUlevel(ulevel);
		return HttpResult.success();
	}

	@PostMapping("/update")
	public HttpResult<String> update(@RequestBody Ulevel ulevel) {

		ulevelService.updateUlevel(ulevel);
		return HttpResult.success();
	}

	@PostMapping("/delete")
	public HttpResult<String> delete(@RequestBody Ulevel ulevel) {

		ulevelService.deleteUlevel(ulevel);
		return HttpResult.success();
	}

	@PostMapping("/list")
	public HttpResult<Page<Ulevel>> list(@RequestParam("page") int pageNum, @RequestParam("size") int pageSize,
			@RequestBody Ulevel dto) {

		Page<Ulevel> page = ulevelService.queryUlevelsByPage(pageNum, pageSize, dto);
		return HttpResult.success(page);
	}

	@GetMapping("/all")
	public List<Ulevel> all() {

		List<Ulevel> list = ulevelService.queryAllUlevels();
		return list;
	}

}
