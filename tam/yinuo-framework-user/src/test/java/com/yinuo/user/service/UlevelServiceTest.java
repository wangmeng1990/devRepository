package com.yinuo.user.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.inuol.user.Ulevel;
import com.yinuo.common.common.GlobalStatus;
import com.yinuo.common.utils.generator.UUIDHexGenerator;
import com.yinuo.user.BaseTest;

@Transactional
public class UlevelServiceTest extends BaseTest {

	@Autowired
	private UlevelService ulevelService;

	private String id = UUIDHexGenerator.generate();

	@Test
	public void testQueryUlevelById() {
		testAddUlevel();

		Ulevel ulevel = ulevelService.queryUlevelById(id);
		Assert.assertNotNull(ulevel);
	}

	@Test
	public void testAddUlevel() {

		Ulevel ulevel = new Ulevel();
		ulevel.setId(id);
		ulevel.setLevelCode("T1");
		ulevel.setLevelName("迷你星");
		ulevel.setBottomLimitation(0);
		ulevel.setTopLimitation(100);
		ulevel.setValidDays(100);
		ulevel.setKeepCondition(20);
		ulevel.setSort(10);
		ulevel.setStatus(GlobalStatus.ON.value);
		ulevelService.addUlevel(ulevel);
	}

	@Test
	public void testUpdateUlevel() {
		testAddUlevel();

		Ulevel ulevel = new Ulevel();
		ulevel = ulevelService.queryUlevelById(id);
		ulevel.setLevelCode("T11");
		ulevelService.updateUlevel(ulevel);

		ulevel = ulevelService.queryUlevelById(id);
		Assert.assertEquals("T11", ulevel.getLevelCode());
	}

	@Test
	public void testDeleteUlevel() {

		testAddUlevel();

		Ulevel ulevel = new Ulevel();
		ulevelService.deleteUlevel(ulevel);

		ulevel = ulevelService.queryUlevelById(id);
		Assert.assertNull(ulevel);
	}

	@Test
	public void testQueryUlevelsByPage() {
		testAddUlevel();

		int pageNum = 1;
		int pageSize = 10;
		Ulevel dto = new Ulevel();
		Page<Ulevel> page = ulevelService.queryUlevelsByPage(pageNum, pageSize, dto);
		Assert.assertEquals(1, page.getTotal());

		dto = new Ulevel();
		dto.setStatus(GlobalStatus.OFF.value);
		page = ulevelService.queryUlevelsByPage(pageNum, pageSize, dto);
		Assert.assertEquals(0, page.getTotal());
	}

	@Test
	public void testQueryAllUlevels() {
		testAddUlevel();

		List<Ulevel> list = ulevelService.queryAllUlevels();
		Assert.assertTrue(list.size() == 1);
	}

}
