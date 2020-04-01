package com.yinuo.user.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.inuol.user.User;
import com.inuol.user.criteria.CriteriaMemberList;
import com.yinuo.user.BaseTest;

@Ignore
public class MemberServiceTest extends BaseTest {

	@Autowired
	private MemberService memberService;

	@Test
	public void testQueryUsersByPage() {
		ZoneId zoneId = ZoneId.systemDefault();

		CriteriaMemberList c = new CriteriaMemberList();
		c.setState(1);
		c.setTimeBegin(Date.from(LocalDate.of(2020, 1, 1).atStartOfDay(zoneId).toInstant()));
		c.setTimeEnd(Date.from(LocalDate.of(2020, 2, 1).atStartOfDay(zoneId).toInstant()));
		c.setUsername("15566667777");

		Page<User> page = memberService.queryUsersByPage(1, 10, c);
		Assert.assertTrue(Long.compare(1, page.getTotal()) == 0);
	}

}
