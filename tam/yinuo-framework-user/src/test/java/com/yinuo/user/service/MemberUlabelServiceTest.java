package com.yinuo.user.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.inuol.dto.user.MemberUlabelTagDto;
import com.inuol.user.Ulabel;
import com.yinuo.user.BaseTest;

@Ignore
public class MemberUlabelServiceTest extends BaseTest {

	@Autowired
	private MemberUlabelService memberUlabelService;

	private Long userId = 1L;

	@Test
	public void testTag() {
		List<String> ulabelIds = Arrays.asList("297ebf7e702de53c01702de53c040000", "297ebf7e702de53c01702de53c050001",
				"297ebf7e702de53c01702de53c050002");

//		List<String> ulabelIds = Arrays.asList("297ebf7e702de53c01702de53c050001",
//				"297ebf7e702de53c01702de53c050002");

		MemberUlabelTagDto dto = new MemberUlabelTagDto();
		dto.setUserId(userId);
		dto.setUlabelIds(ulabelIds);
		memberUlabelService.tag(dto);
	}

	@Test
	public void testQueryUlabelsByUserId() {

		List<Ulabel> list = memberUlabelService.queryUlabelsByUserId(userId);
		Assert.assertTrue(list.size() == 3);
	}

}
