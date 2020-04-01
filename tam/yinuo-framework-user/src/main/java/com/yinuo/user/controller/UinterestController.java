package com.yinuo.user.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.inuol.user.Uinterest;
import com.inuol.user.Ulevel;
import com.inuol.vo.user.UinterestVo;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.utils.generator.UUIDHexGenerator;
import com.yinuo.user.service.UinterestService;
import com.yinuo.user.service.UlevelService;

/**
 * 会员权益controller
 * 
 * @author weiss
 *
 */
@RestController
@RequestMapping("/uinterest")
public class UinterestController {

	@Autowired
	private UinterestService uinterestService;
	@Autowired
	private UlevelService ulevelService;

	@PostMapping("/add")
	public HttpResult<String> add(@RequestBody Uinterest uinterest) {

		uinterest.setId(UUIDHexGenerator.generate());
		uinterestService.addUinterest(uinterest);
		return HttpResult.success();
	}

	@PostMapping("/update")
	public HttpResult<String> update(@RequestBody Uinterest uinterest) {

		uinterestService.updateUinterest(uinterest);
		return HttpResult.success();
	}

	@PostMapping("/delete")
	public HttpResult<String> delete(@RequestBody Uinterest uinterest) {

		uinterestService.deleteUinterest(uinterest);
		return HttpResult.success();
	}

	@PostMapping("/list")
	public HttpResult<Page<UinterestVo>> list(@RequestParam("page") int pageNum, @RequestParam("size") int pageSize,
			@RequestBody Uinterest dto) {

		Page<Uinterest> page = uinterestService.queryUinterestsByPage(pageNum, pageSize, dto);
		Page<UinterestVo> p = new Page<>(pageNum, pageSize);
		p.setTotal(page.getTotal());
		page.forEach(t -> {
			UinterestVo uv = new UinterestVo();
			BeanUtils.copyProperties(t, uv);

			Ulevel ulevel = ulevelService.queryUlevelById(t.getUlevelId());
			if (ulevel != null) {
				uv.setUlevelName(ulevel.getLevelName());
			}
		});

		return HttpResult.success(p);
	}

}
