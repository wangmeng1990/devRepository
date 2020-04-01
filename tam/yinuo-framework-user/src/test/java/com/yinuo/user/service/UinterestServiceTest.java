package com.yinuo.user.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.inuol.user.Uinterest;
import com.yinuo.common.common.GlobalStatus;
import com.yinuo.common.utils.generator.UUIDHexGenerator;
import com.yinuo.user.BaseTest;

@Transactional
public class UinterestServiceTest extends BaseTest {

	@Autowired
	private UinterestService uinterestService;

	private String id = UUIDHexGenerator.generate();

	@Test
	public void testQueryUinterestById() {
		testAddUinterest();

		Uinterest uinterest = uinterestService.queryUinterestById(id);
		Assert.assertNotNull(uinterest);
	}

	@Test
	public void testAddUinterest() {

		Uinterest uinterest = new Uinterest();
		uinterest.setId(id);
		uinterest.setUlevelId(UUIDHexGenerator.generate());
		uinterest.setInterestName("基础勋章");
		uinterest.setGoldStar(20);
		uinterest.setWelfareId(UUIDHexGenerator.generate());
		uinterest.setIconUrl(
				"https://www.baidu.com/s?wd=%e6%8a%97%e7%96%ab%e5%bf%97%e6%84%bf%e8%80%85&sa=ire_dl_gh_logo&rsv_dl=igh_logo_pcs");
		uinterest.setDescription("描述说明");
		uinterest.setStatus(GlobalStatus.ON.value);

		uinterestService.addUinterest(uinterest);
	}

	@Test
	public void testUpdateUinterest() {
		testAddUinterest();

		String name = "基础勋章2";
		Uinterest uinterest = uinterestService.queryUinterestById(id);
		uinterest.setInterestName(name);
		uinterestService.updateUinterest(uinterest);

		uinterest = uinterestService.queryUinterestById(id);
		Assert.assertEquals(name, uinterest.getInterestName());
	}

	@Test
	public void testDeleteUinterest() {
		testAddUinterest();

		Uinterest uinterest = new Uinterest();
		uinterest.setId(id);
		uinterestService.deleteUinterest(uinterest);

		uinterest = uinterestService.queryUinterestById(id);
		Assert.assertNull(uinterest);
	}

	@Test
	public void testQueryUinterestsByPage() {
		int pageNum = 1;
		int pageSize = 10;
		Uinterest dto = new Uinterest();

		// case 1: 插入数据之前
		Page<Uinterest> page = uinterestService.queryUinterestsByPage(pageNum, pageSize, dto);
		Assert.assertEquals(0, page.getTotal());

		// case 2：插入数据之后
		testAddUinterest();
		dto = new Uinterest();
		page = uinterestService.queryUinterestsByPage(pageNum, pageSize, dto);
		Assert.assertEquals(1, page.getTotal());

	}

	@Test
	public void testQueryAllUinterests() {

		List<Uinterest> list = uinterestService.queryAllUinterests();
		Assert.assertEquals(0, list.size());

		testAddUinterest();
		list = uinterestService.queryAllUinterests();
		Assert.assertEquals(1, list.size());
	}

}
