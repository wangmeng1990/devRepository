package com.yinuo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inuol.user.Ulabel;
import com.yinuo.common.common.IsValid;
import com.yinuo.user.mapper.UlabelMapper;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户标签接口
 * 
 * @author weiss
 *
 */
@Slf4j
@Service
@Transactional
public class UlabelService {

	@Autowired
	private UlabelMapper ulabelMapper;

	public List<Ulabel> queryAllUlabels() {
		log.info("查询所有用户标签");
		Example example = new Example(Ulabel.class);
		example.orderBy("id");

		Example.Criteria c = example.createCriteria();
		c.andEqualTo("isValid", IsValid.VALID.value);

		List<Ulabel> list = ulabelMapper.selectByExample(example);

		log.info("查询所有用户标签的返回值：size={}", list.size());
		return list;
	}
}
