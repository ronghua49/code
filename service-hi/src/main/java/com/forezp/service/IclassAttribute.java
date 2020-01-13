package com.forezp.service;

import com.forezp.entity.XyjControllerURL;
import com.forezp.entity.classInfo;
import com.forezp.entity.entityName;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

@Headers({"Content-Type: application/json", "Accept: application/json", "token: fsvssdfsdf"})
public interface IclassAttribute {	
	
	/**
	 * @param servceid //微服务serviceid
	 * @param classname //此微服务实例下的类名称
	 * @return 获取实体类属性信息
	 */
	@RequestLine("GET /EureKaUtiles/getClassAttr?classFullName={classFullName}")
	public List<classInfo> getClassAttr(@Param(value = "classFullName") String classFullName);
	/**
	 * @return 返回微服务中的实体类列表
	 */
	@RequestLine("GET /EureKaUtiles/getEntityClass")
	public List<entityName> getEntityClass();
	
	/**
	 * @return 返回微服务中的实体类列表
	 */
	@RequestLine("GET /EureKaUtiles/getAllURL")
	public List<XyjControllerURL> getAllURL();

}
