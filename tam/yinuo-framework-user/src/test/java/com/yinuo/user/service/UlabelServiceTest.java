package com.yinuo.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.inuol.user.Ulabel;
import com.yinuo.common.common.IsValid;
import com.yinuo.common.utils.generator.UUIDHexGenerator;
import com.yinuo.user.BaseTest;
import com.yinuo.user.mapper.UlabelMapper;

@Ignore
public class UlabelServiceTest extends BaseTest {

	@Autowired
	private UlabelService ulabelService;

	@Autowired
	private UlabelMapper ulabelMapper;

	@Test
	public void testQueryAllUlabels() {

		List<Ulabel> list = ulabelService.queryAllUlabels();
		Assert.assertTrue(Integer.compare(3, list.size()) == 0);
	}

	@Test
	public void testInsert() {
		List<Ulabel> list = new ArrayList<>();
		list.add(buildUlabel("羊毛党"));
		list.add(buildUlabel("种子用户"));
		list.add(buildUlabel("活跃用户"));

		for (Ulabel ul : list) {
			ulabelMapper.insert(ul);
		}
	}

	private Ulabel buildUlabel(String name) {
		Ulabel ul = new Ulabel();
		ul.setId(UUIDHexGenerator.generate());
		ul.setName(name);
		ul.setCreateTime(new Date());
		ul.setUpdateTime(new Date());
		ul.setCreateUser("init");
		ul.setUpdateUser("init");
		ul.setIsValid(IsValid.VALID.value);

		return ul;
	}
}
