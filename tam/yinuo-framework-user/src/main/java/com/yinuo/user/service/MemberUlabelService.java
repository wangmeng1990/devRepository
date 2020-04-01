package com.yinuo.user.service;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.inuol.dto.user.MemberUlabelTagDto;
import com.inuol.user.MemberUlabel;
import com.inuol.user.Ulabel;
import com.yinuo.common.common.BusinessException;
import com.yinuo.common.common.IsValid;
import com.yinuo.common.utils.generator.UUIDHexGenerator;
import com.yinuo.user.mapper.MemberUlabelMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户的标签关系管理
 * 
 * @author weiss
 *
 */
@Slf4j
@Service
@Transactional
public class MemberUlabelService {

	@Autowired
	private MemberUlabelMapper memberUlabelMapper;

	/**
	 * 给用户打标签
	 * 
	 * @param dto
	 */
	public void tag(MemberUlabelTagDto dto) {
		log.info("给用户打标签参数：dto={}", dto);

		Long userId = dto.getUserId();
		if (userId == null) {
			throw new BusinessException("", "用户id不能为空");
		}

		List<String> ulabelIds = dto.getUlabelIds();
		if (CollectionUtils.isEmpty(ulabelIds)) {
			throw new BusinessException("", "标签id不能为空");
		}
		if (ulabelIds.size() > 10) {
			throw new BusinessException("", "最多关联10个标签");
		}
		long distinctCount = ulabelIds.stream().distinct().count();
		if (distinctCount < ulabelIds.size()) {
			throw new BusinessException("", "存在重复的标签id");
		}

		int result = 0;
		Date now = new Date();
		List<String> ulabelIdsFromDb = queryUlabelsByUserId(userId).stream().map(Ulabel::getId).collect(toList());
		for (String ulabelId : ulabelIds) {
			if (ulabelIdsFromDb.remove(ulabelId)) {
				continue;
			}
			MemberUlabel mu = new MemberUlabel();
			mu.setId(UUIDHexGenerator.generate());
			mu.setUserId(userId);
			mu.setUlabelId(ulabelId);
			mu.setCreateTime(now);
			mu.setUpdateTime(now);
			mu.setIsValid(IsValid.VALID.value);

			result += memberUlabelMapper.insert(mu);
		}

		if (ulabelIdsFromDb.size() > 0) {
			result += memberUlabelMapper.deleteMemberUlabels(userId, ulabelIdsFromDb);
		}

		log.info("给用户打标签的返回值：result={}", result);
	}

	/**
	 * 查询用户的标签
	 * 
	 * @param userId
	 * @return
	 */
	public List<Ulabel> queryUlabelsByUserId(Long userId) {
		log.info("查询用户标签参数：userId={}", userId);

		List<Ulabel> list = memberUlabelMapper.queryUlabelsByUserId(userId);

		log.info("查询用户标签的返回值：size={}", list.size());
		return list;
	}

}
