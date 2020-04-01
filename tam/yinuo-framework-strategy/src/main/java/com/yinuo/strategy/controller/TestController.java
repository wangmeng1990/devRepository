package com.yinuo.strategy.controller;

import com.inuol.entity.strategy.Travel;
import com.yinuo.common.common.BusinessException;
import com.yinuo.common.common.HttpResult;
import com.yinuo.common.common.ListResult;
import com.yinuo.common.pojo.UserInfo;
import com.yinuo.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class TestController extends BaseController {
	/*@Autowired
	private UserApi userApi;
	@GetMapping("/test")
    public ResponseEntity<User> test(){
		User user = userApi.queryUser("11", "111");
        return ResponseEntity.ok(user);
    }*/
	@Autowired
	StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串

	@Autowired
	RedisTemplate redisTemplate;//操作k-v对象的

	@GetMapping("/test2")
	public HttpResult<ListResult<Travel>> test2(){
		ListResult<Travel> listResult = new ListResult<Travel>();
	    List<Travel> list = new ArrayList<Travel>();
		list.add(new Travel());
		listResult.setTotal(10L);
		listResult.setRows(list);
		return HttpResult.success(10,list);
	}

	@GetMapping("/test3")
	public HttpResult test3(){

		throw new BusinessException("600","请不要重复保存");
		/*stringRedisTemplate.opsForValue().append("aaaKey", "aaaValue");
		String msg = stringRedisTemplate.opsForValue().get("aaaKey");*/
		//String token = this.request.getHeader("token");
		//String uid = this.request.getHeader("uid");
		//UserInfo userInfo = (UserInfo)this.request.getAttribute("userInfo");
		//return HttpResult.success(userInfo);
	}
}
