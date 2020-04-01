package com.yinuo.gateway.filter;


import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.yinuo.common.pojo.UserInfo;
import com.yinuo.common.utils.JwtUtils;
import com.yinuo.gateway.config.FilterProperties;
import com.yinuo.gateway.config.ZuulJwtProperties;
import com.yinuo.gateway.zuulResponse.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
@EnableConfigurationProperties({ZuulJwtProperties.class, FilterProperties.class})
public class LoginFilter extends ZuulFilter {

    private  final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Autowired
    private ZuulJwtProperties zuulJwtProperties;

    @Autowired
    private FilterProperties filterProperties;

    /*@Autowired
    private StringRedisTemplate stringRedisTemplate;*/

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String key = "tiananmen";

    private static final String authKeyList = "tamauthLsit";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        // 获取白名单
        List<String> allowPaths = this.filterProperties.getAllowPaths();

        // 初始化运行上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 获取request对象
        HttpServletRequest request = context.getRequest();
        // 获取请求的路径
        String url = request.getRequestURL().toString();

        for (String allowPath : allowPaths) {
            if(StringUtils.contains(url, allowPath)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 初始化运行上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 获取request对象
        HttpServletRequest request = context.getRequest();
        // 获取请求的路径
        String url = request.getRequestURL().toString();

        //String token = CookieUtils.getCookieValue(request, this.zuulJwtProperties.getCookieName());
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)){
            access_denied(false,"此操作需要登录！！！");
        } else {
            try {
                //如果出现异常说明解析失败
                UserInfo userInfo = JwtUtils.getInfoFromToken(token, this.zuulJwtProperties.getPublicKey());
                if(!userInfo.getTerminal().equals("app")){
                    List<Map<String,String>> authList = getAuthList(userInfo.getRoleId());
                    if(authList!=null){
                        for (Map<String,String> authUrl:authList) {
                            if(StringUtils.contains(url, authUrl.get("url"))){
                                context.addZuulRequestHeader("uid",userInfo.getId().toString());
                                return null;
                            }
                        }
                    }
                    access_denied(false,"您无权此操作！！！");
                }
                context.addZuulRequestHeader("uid",userInfo.getId().toString());
            } catch (Exception e) {
                e.printStackTrace();
                access_denied(false,"身份校验失败！！！");
            }

        }
       return null;
    }



    //根据角色id查询用户权限集合
    public List<Map<String,String>> getAuthList(String roleId){
        /*String strlist = stringRedisTemplate.opsForValue().get(key + roleId);
        List<Map> array = JSON.parseArray(strlist, Map.class);*/
        List<Map<String,String>> array = (List<Map<String,String>>)redisTemplate.boundHashOps(authKeyList).get(key + roleId);
        return array;
    }

    //拒绝访问
    private void access_denied(boolean success,String message){
        RequestContext requestContext = RequestContext.getCurrentContext();
        //得到response
        HttpServletResponse response = requestContext.getResponse();
        //拒绝访问
        requestContext.setSendZuulResponse(false);
        //构建响应的信息
        ResponseResult responseResult = new ResponseResult(success,message);
        //转成json
        String jsonString = JSON.toJSONString(responseResult);
        requestContext.setResponseBody(jsonString);
        //转成json，设置contentType
        response.setContentType("application/json;charset=utf-8");
    }

}
