package com.xyjsoft.syspay.controller;

import java.util.List;

import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xyjsoft.core.query.PageList;
import com.xyjsoft.core.query.QueryFilter;
import com.xyjsoft.core.http.HttpResult;
import com.xyjsoft.core.page.PageRequest;
import com.xyjsoft.syspay.model.SysPay;
import com.xyjsoft.syspay.service.SysPayService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * ---------------------------
 * 支付宝微信支付设置表 (SysPayController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-06 17:23:12
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("sysPay")
@Api(tags="SysPayController 支付宝微信支付设置表")
public class SysPayController {

	@Autowired
	private SysPayService sysPayService;

	/**
	 * 保存支付宝微信支付设置表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	@ApiOperation(value = "新增,更新支付宝微信支付设置表数据", httpMethod = "POST", notes = "新增,更新支付宝微信支付设置表数据")
	public HttpResult save(@ApiParam(name="SysPay",value="支付宝微信支付设置表业务对象") @RequestBody SysPay record) {
		return HttpResult.ok(sysPayService.save(record));
	}

    /**
     * 删除支付宝微信支付设置表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	@ApiOperation(value = "删除支付宝微信支付设置表记录", httpMethod = "POST", notes = "删除支付宝微信支付设置表记录")
	public HttpResult delete(@ApiParam(name="SysPay列表",value="SysPay列表")@RequestBody List<SysPay> records) {
		return HttpResult.ok(sysPayService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	@ApiOperation(value="支付宝微信支付设置表数据列表", httpMethod = "POST", notes = "获取支付宝微信支付设置表列表")
	public HttpResult findPage(@ApiParam(name="PageRequest",value="查询对象")@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysPayService.findPage(pageRequest));
	}
    /**
     * 根据主键查询
     * @param id
     * @return
     */ 	
	@GetMapping(value="/findById")
	@ApiOperation(value="支付宝微信支付设置表数据详情",httpMethod = "GET",notes = "支付宝微信支付设置表数据详情")
	public HttpResult findById(@ApiParam(name="id",value="业务对象主键")@RequestParam Long id) {
		return HttpResult.ok(sysPayService.findById(id));
	}
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */ 	
	@GetMapping(value="/get")
	@ApiOperation(value="支付宝微信支付设置表数据详情",httpMethod = "GET",notes = "支付宝微信支付设置表数据详情")
	public HttpResult get() {
		SysPay sysPay = sysPayService.get();
		return HttpResult.ok(sysPay);
	}
	/**
     * 查询
     * @param pageRequest
     * @return
     * @throws Exception 
     * @throws SystemException 
     */    
	@PostMapping(value="/query")
	@ApiOperation(value="query查询", httpMethod = "POST", notes = "query查询")
	public HttpResult query(@ApiParam(name="QueryFilter",value="查询对象")@RequestBody QueryFilter queryFilter) throws SystemException, Exception {
		PageList<SysPay> query = sysPayService.query(queryFilter);
		return HttpResult.ok(query);
	}
	
	
	
	
}
