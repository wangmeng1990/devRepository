package com.yinuo.user.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.inuol.user.Ulabel;
import com.inuol.user.Ulevel;
import com.inuol.user.User;
import com.inuol.user.criteria.CriteriaMemberList;
import com.inuol.user.criteria.CriteriaUser;
import com.yinuo.common.common.HttpResult;
import com.yinuo.user.service.MemberService;
import com.yinuo.user.service.MemberUlabelService;
import com.yinuo.user.service.UlevelService;

import static java.util.stream.Collectors.joining;

/**
 * 会员基本信息
 * 
 * @author weiss
 *
 */
@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberUlabelService memberUlabelService;
	@Autowired
	private UlevelService ulevelService;

	@PostMapping("/list")
	public HttpResult<Page<CriteriaUser>> list(@RequestParam("page") int pageNum, @RequestParam("size") int pageSize,
			@RequestBody CriteriaMemberList criteriaUser) {

		Page<User> page = memberService.queryUsersByPage(pageNum, pageSize, criteriaUser);

		Page<CriteriaUser> p = new Page<>(pageNum, pageSize);
		p.setTotal(page.getTotal());
		page.getResult().forEach(t -> {
			// 设置标签名字
			CriteriaUser cu = new CriteriaUser();
			BeanUtils.copyProperties(t, cu);
			List<Ulabel> list = memberUlabelService.queryUlabelsByUserId(t.getId());
			Ulevel ulevel = ulevelService.queryUlevelById(t.getUlevelId());

			cu.setUlabelNames(list.stream().map(Ulabel::getName).collect(joining("、")));
			if (ulevel != null) {
				cu.setUlevelName(ulevel.getLevelName());
			}
			p.add(cu);
		});
		return HttpResult.success(p);
	}
}
