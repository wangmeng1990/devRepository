package com.yinuo.user.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inuol.user.Uinterest;
import com.yinuo.common.common.BusinessException;
import com.yinuo.common.common.GlobalStatus;
import com.yinuo.common.common.IsValid;
import com.yinuo.user.mapper.UinterestMapper;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

import static java.util.stream.Collectors.toList;

/**
 * 会员权益接口
 * 
 * @author weiss
 *
 */
@Slf4j
@Service
public class UinterestService {

	@Autowired
	private UinterestMapper uinterestMapper;

	/**
	 * 根据id查询会员权益
	 * 
	 * @param id
	 * @return
	 */
	public Uinterest queryUinterestById(String id) {
		log.info("根据id查询会员权益参数：id={}", id);

		Uinterest uinterest = uinterestMapper.selectByPrimaryKey(id);
		uinterest = uinterest.getIsValid() == IsValid.DELETED.value ? null : uinterest;
		log.info("根据id查询会员权益的返回值：uinterest={}", uinterest);
		return uinterest;
	}

	/**
	 * 新增会员权益
	 * 
	 * @param uinterest
	 * @return
	 */
	public int addUinterest(Uinterest uinterest) {
		log.info("新增会员权益参数：uinterest={}", uinterest);

		checkFormParam(uinterest);

		Date now = new Date();
		uinterest.setCreateTime(now);
		uinterest.setUpdateTime(now);
		uinterest.setIsValid(IsValid.VALID.value);
		int count = uinterestMapper.insert(uinterest);

		log.info("新增会员权益的返回值：count={}", count);
		return count;
	}

	/** 检查表单参数 */
	private void checkFormParam(Uinterest uinterest) {

		if (StringUtils.isBlank(uinterest.getUlevelId())) {
			throw new BusinessException("", "会员等级不能为空");
		}

		if (StringUtils.isBlank(uinterest.getInterestName())) {
			throw new BusinessException("", "会员权益不能为空");
		}

		if (uinterest.getGoldStar() == null) {
			throw new BusinessException("", "赠送金星不能为空");
		}
		if (uinterest.getGoldStar() <= 0) {
			throw new BusinessException("", "赠送金星不能小于等于0");
		}

		if (StringUtils.isBlank(uinterest.getWelfareId())) {
			throw new BusinessException("", "配置福利不能为空");
		}

		if (StringUtils.isBlank(uinterest.getIconUrl())) {
			throw new BusinessException("", "权益icon不能为空");
		}

		GlobalStatus.checkValidStatus(uinterest.getStatus());

		List<Uinterest> allUinterests = queryAllUinterests();
		allUinterests = allUinterests.stream().filter(t -> t.getId().equals(uinterest.getId()) == false)
				.collect(toList());
		for (Uinterest ui : allUinterests) {
			if (uinterest.getUlevelId().equals(ui.getUlevelId())) {
				throw new BusinessException("", "会员等级已使用");
			}

			if (uinterest.getInterestName().equals(ui.getInterestName())) {
				throw new BusinessException("", "会员权益已存在");
			}
		}
	}

	/**
	 * 编辑会员权益
	 * 
	 * @param uinterest
	 * @return
	 */
	public int updateUinterest(Uinterest uinterest) {
		log.info("编辑会员权益参数：uinterest={}", uinterest);

		checkExistsById(uinterest.getId());
		checkFormParam(uinterest);

		Date now = new Date();
		uinterest.setUpdateTime(now);
		int count = uinterestMapper.updateByPrimaryKeySelective(uinterest);

		log.info("编辑会员权益的返回值：count={}", count);
		return count;
	}

	private Uinterest checkExistsById(String id) {
		Uinterest fromDb = queryUinterestById(id);
		if (fromDb == null) {
			throw new BusinessException("", "会员权益不存在");
		}

		return fromDb;
	}

	/**
	 * 删除会员权益
	 * 
	 * @param uinterest
	 * @return
	 */
	public int deleteUinterest(Uinterest uinterest) {
		log.info("删除会员权益参数：uinterest={}", uinterest);

		Uinterest fromDb = checkExistsById(uinterest.getId());

		Date now = new Date();
		fromDb.setUpdateTime(now);
		fromDb.setIsValid(IsValid.DELETED.value);
		fromDb.setUpdateUser(uinterest.getUpdateUser());
		int count = uinterestMapper.updateByPrimaryKeySelective(fromDb);

		log.info("删除会员权益的返回值：count={}", count);
		return count;
	}

	/**
	 * 分页查询会员权限
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param dto
	 * @return
	 */
	public Page<Uinterest> queryUinterestsByPage(int pageNum, int pageSize, Uinterest dto) {
		log.info("分页查询会员权益参数：pageNum={},pageSize={},dto={}", pageNum, pageSize, dto);

		PageHelper.startPage(pageNum, pageSize);
		Example example = new Example(Uinterest.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("isValid", IsValid.VALID.value);

		example.orderBy("updateTime").desc();
		List<Uinterest> list = uinterestMapper.selectByExample(example);

		log.info("分页查询会员权益的返回值：size={}", list.size());
		return (Page<Uinterest>) list;
	}

	/**
	 * 查询所有会员权益
	 * 
	 * @return
	 */
	public List<Uinterest> queryAllUinterests() {
		log.info("查询所有会员权益");

		Example example = new Example(Uinterest.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("isValid", IsValid.VALID.value);

		example.orderBy("interestName").asc();
		List<Uinterest> list = uinterestMapper.selectByExample(example);

		log.info("查询所有会员的返回值：size={}", list.size());
		return list;
	}

}
