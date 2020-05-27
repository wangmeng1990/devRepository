package com.ark.hngxt.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ark.dependencies.common.auth.model.CurrentUser;
import com.ark.dependencies.common.auth.util.UserContext;
import com.ark.hngxt.product.model.ProductIntelligentMatchVO;
import com.ark.hngxt.product.service.MatchProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "pc产品智能匹配")
@RestController
@RequestMapping("/")
public class ProductIntelligentMatchController {
	@Autowired 
	private MatchProductService matchProductService;
	
	@ApiOperation(value = "产品智能匹配查询")
	@PostMapping("/free/queryProductIntelligentMatchList")
	public ResponseEntity<List<ProductIntelligentMatchVO>> queryProductIntelligentMatchList() {
		CurrentUser currentUser = UserContext.currentUser();
		List<ProductIntelligentMatchVO> list=matchProductService.queryProductIntelligentMatchList(currentUser,"1");
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
}
