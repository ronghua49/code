package com.haohua.prictice;

import com.alibaba.fastjson.JSON;
import com.haohua.prictice.utils.HttpResult;
import com.haohua.prictice.utils.JwtTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController("SysRedisController")
@RequestMapping("sysRedis")
@Api(tags="SysRedisController Redis操作信息")
public class SysRedisController {
	private Logger logger = LoggerFactory.getLogger(SysRedisController.class);
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	@PostMapping(value = "/saveloginInfo")
	@ApiOperation(value = "保存登录信息", httpMethod = "POST", notes = "登录信息存储到redis中")
	public HttpResult saveloginInfo(@ApiParam(name="LoginInfo",value="登陆员工对象") @RequestBody LoginInfo record){
		try{
			redisTemplate.opsForValue().set(record.getToken(), JSON.toJSONString(record) , JwtTokenUtils.EXPIRE_TIME,TimeUnit.MILLISECONDS);
		}catch(Exception e) {
			throw new RuntimeException("Redis操作异常");
		}
		return HttpResult.ok("登录信息保存成功");
	}

	/**
	 * @describe 获取登录信息
	 * @author sjg 
	 * @date 2019年5月24日 上午11:26:44
	 * @version 1.0
	 * @param token
	 * @return
	 */
	public LoginInfo getLoginUserInfo(String token){
		LoginInfo loginInfo = null;
		try{
			Boolean hasKey = redisTemplate.hasKey(token);
			if(!hasKey) {
				return null;
			}
			loginInfo = JSON.parseObject((String) redisTemplate.opsForValue().get(token), LoginInfo.class);
			redisTemplate.opsForValue().set(token, JSON.toJSONString(loginInfo),JwtTokenUtils.EXPIRE_TIME,TimeUnit.MILLISECONDS);
		}catch(Exception e) {
			logger.error("Redis读取信息异常", e);
			throw new RuntimeException("Redis读取信息异常");
		}
		return loginInfo;
	}
}
