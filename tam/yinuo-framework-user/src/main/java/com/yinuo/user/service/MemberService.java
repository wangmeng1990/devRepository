package com.yinuo.user.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inuol.user.User;
import com.inuol.user.criteria.CriteriaMemberList;
import com.yinuo.user.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

@Slf4j
@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;

	public Page<User> queryUsersByPage(int pageNum, int pageSize, CriteriaMemberList criteriaUser) {
		log.info("分页查询会员列表参数：pageNum={},pageSize={},criteriaUser={}", pageNum, pageSize, criteriaUser);

		PageHelper.startPage(pageNum, pageSize);

		Example example = new Example(User.class);
		Example.Criteria c = example.createCriteria();
		if (criteriaUser.getState() != null && criteriaUser.getState() != -1) {
			c.andEqualTo("state", criteriaUser.getState());
		}
		if (StringUtils.isNotEmpty(criteriaUser.getUsername())) {
			c.andLike("username", "%" + criteriaUser.getUsername() + "%");
		}
		if (criteriaUser.getTimeBegin() != null && criteriaUser.getTimeEnd() != null) {
			c.andBetween("time", criteriaUser.getTimeBegin(), criteriaUser.getTimeEnd());
		}
		if (StringUtils.isNotEmpty(criteriaUser.getUlevelId())) {
			c.andEqualTo("ulevelId", criteriaUser.getUlevelId());
		}
		
		List<User> list = memberMapper.selectByExample(example);

		log.info("分页查询会员列表的返回值：size={}", list.size());
		return (Page<User>) list;
	}

}
