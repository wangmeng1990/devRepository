package com.yinuo.auth.client;



import org.springframework.cloud.openfeign.FeignClient;

import com.yinuo.api.user.UserApi;

@FeignClient("user")
public interface UserClient extends UserApi {
}
