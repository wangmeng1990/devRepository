package com.yinuo.user.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.inuol.user.Ulevel;
import com.yinuo.common.common.BusinessException;
import com.yinuo.common.common.GlobalStatus;
import com.yinuo.common.common.IsValid;
import com.yinuo.user.mapper.UlevelMapper;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

import static java.util.stream.Collectors.toList;

/**
 * 会员等级接口
 * 
 * @author weiss
 *
 */
@Slf4j
@Service
@Transactional
public class UlevelService {

	@Autowired
	private UlevelMapper ulevelMapper;

	/**
	 * 根据Id查询会员等级
	 * 
	 * @param id
	 * @return
	 */
	public Ulevel queryUlevelById(String id) {
		log.info("根据Id查询会员等级参数：id={}", id);

		Example example = new Example(Ulevel.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("id", id);
		c.andEqualTo("isValid", IsValid.VALID.value);

		Ulevel ulevel = ulevelMapper.selectOneByExample(example);
		log.info("根据Id查询会员等级的返回值：ulevel={}", ulevel);
		return ulevel;
	}

	public int addUlevel(Ulevel ulevel) {
		log.info("新增会员等级参数：ulevel={}", ulevel);

		checkValidParam(ulevel);

		Date now = new Date();
		ulevel.setCreateTime(now);
		ulevel.setUpdateTime(now);
		ulevel.setIsValid(IsValid.VALID.value);
		int count = ulevelMapper.insert(ulevel);
		log.info("新增会员等级的返回值：count={}", count);
		return count;
	}

	/** 检查参数的有效性 */
	private void checkValidParam(Ulevel ulevel) {

		if (StringUtils.isBlank(ulevel.getLevelCode())) {
			throw new BusinessException("", "会员等级不能为空");
		}
		if (StringUtils.isBlank(ulevel.getLevelName())) {
			throw new BusinessException("", "会员等级名称不能为空");
		}

		if (ulevel.getBottomLimitation() == null) {
			throw new BusinessException("", "成长值下限不能为空");
		}
		if (ulevel.getBottomLimitation() < 0) {
			throw new BusinessException("", "成长值下限不能小于0");
		}

		if (ulevel.getTopLimitation() == null) {
			throw new BusinessException("", "成长值上限不能为空");
		}
		if (ulevel.getBottomLimitation() > ulevel.getTopLimitation()) {
			throw new BusinessException("", "成长值下限不能大于成长值上限");
		}

		if (ulevel.getValidDays() == null) {
			throw new BusinessException("", "有效期/天不能为空");
		}
		if (ulevel.getValidDays() <= 0) {
			throw new BusinessException("", "有效期/天不能小于等于0");
		}

		if (ulevel.getKeepCondition() == null) {
			throw new BusinessException("", "保级条件不能为空");
		}
		if (ulevel.getKeepCondition() < 0) {
			throw new BusinessException("", "保级条件不能小于0");
		}

		GlobalStatus.checkValidStatus(ulevel.getStatus());

		List<Ulevel> allUlevels = queryAllUlevels();
		allUlevels = allUlevels.stream().filter(t -> t.getId().equals(ulevel.getId()) == false).collect(toList());
		for (Ulevel t : allUlevels) {
			if (ulevel.getLevelCode().equals(t.getLevelCode())) {
				throw new BusinessException("", "会员等级已存在");
			}
			if (ulevel.getLevelName().equals(t.getLevelName())) {
				throw new BusinessException("", "会员等级名称已存在");
			}
			boolean notOverlap = t.getTopLimitation() < ulevel.getBottomLimitation()
					|| ulevel.getTopLimitation() < t.getBottomLimitation();
			if (notOverlap == false) {
				throw new BusinessException("", "成长值上下限与别的等级有重叠");
			}
		}
	}

	/**
	 * 编辑会员等级
	 * 
	 * @param ulevel
	 * @return
	 */
	public int updateUlevel(Ulevel ulevel) {
		log.info("编辑会员等级参数：ulevel={}", ulevel);

		checkExistsById(ulevel.getId());
		checkValidParam(ulevel);

		Date now = new Date();
		ulevel.setUpdateTime(now);
		int count = ulevelMapper.updateByPrimaryKeySelective(ulevel);

		log.info("编辑会员等级的返回值：count={}", count);
		return count;
	}

	/** 检查id是否存在 */
	private Ulevel checkExistsById(String id) {

		Ulevel ulevel = queryUlevelById(id);
		if (ulevel == null || ulevel.getIsValid() == IsValid.DELETED.value) {
			throw new BusinessException("", "会员等级不存在");
		}

		return ulevel;
	}

	/**
	 * 删除会员等级
	 * 
	 * @param ulevel
	 * @return
	 */
	public int deleteUlevel(Ulevel ulevel) {
		log.info("删除会员等级参数：ulevel={}", ulevel);

		Ulevel fromDb = checkExistsById(ulevel.getId());
		fromDb.setIsValid(IsValid.DELETED.value);
		fromDb.setUpdateUser(ulevel.getUpdateUser());
		fromDb.setUpdateTime(new Date());

		int count = ulevelMapper.updateByPrimaryKeySelective(fromDb);

		log.info("删除会员等级的返回值：count={}", count);
		return count;
	}

	/**
	 * 分页查询会员等级
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param dto
	 * @return
	 */
	public Page<Ulevel> queryUlevelsByPage(int pageNum, int pageSize, Ulevel dto) {
		log.info("分页查询会员等级参数：pageNum={},pageSize={},dto={}", pageNum, pageSize, dto);
		PageHelper.startPage(pageNum, pageSize);
		Example example = new Example(Ulevel.class);
		Example.Criteria c = example.createCriteria();
		if (dto.getStatus() != null) {
			if (dto.getStatus() > 0) {
				c.andEqualTo("status", dto.getStatus());
			}
		}
		example.orderBy("sort").asc();

		List<Ulevel> list = ulevelMapper.selectByExample(example);

		log.info("分页查询会员等级的返回值：size={}", list.size());
		return (Page<Ulevel>) list;

	}

	/**
	 * 查询所有的会员等级
	 * 
	 * @return
	 */
	public List<Ulevel> queryAllUlevels() {
		log.info("查询所有会员等级");
		Example example = new Example(Ulevel.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("isValid", IsValid.VALID.value);

		List<Ulevel> list = ulevelMapper.selectByExample(example);

		log.info("查询所有会员等级的返回值：size={}", list.size());
		return list;
	}
}
