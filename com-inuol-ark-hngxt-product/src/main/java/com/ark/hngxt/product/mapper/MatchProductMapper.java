package com.ark.hngxt.product.mapper;

import java.util.List;

import com.ark.hngxt.product.domain.MatchProduct;
import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface MatchProductMapper extends BaseMapper<MatchProduct>{
	List<MatchProduct> queryMatchProduct();
}
