package com.xyjsoft.syspay.controller;

import java.util.List;
import java.util.Map;

import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xyjsoft.core.query.PageList;
import com.xyjsoft.core.query.QueryFilter;
import com.xyjsoft.core.http.HttpResult;
import com.xyjsoft.core.page.PageRequest;
import com.xyjsoft.syspay.model.SysPayRecord;
import com.xyjsoft.syspay.service.SysPayRecordService;
import com.xyjsoft.syspay.vo.PayType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * ---------------------------
 * 支付信息表 (SysPayRecordController)         
 * ---------------------------
 * 作者：  kitty-generator
 * 时间：  2019-03-06 17:23:12
 * 说明：  我是由代码生成器生生成的
 * ---------------------------
 */
@RestController
@RequestMapping("sysPayRecord")
@Api(tags="SysPayRecordController 支付信息表")
public class SysPayRecordController {

	@Autowired
	private SysPayRecordService sysPayRecordService;

	/**
	 * 保存支付信息表
	 * @param record
	 * @return
	 */	
	@PostMapping(value="/save")
	@ApiOperation(value = "新增,更新支付信息表数据", httpMethod = "POST", notes = "新增,更新支付信息表数据")
	public HttpResult save(@ApiParam(name="SysPayRecord",value="支付信息表业务对象") @RequestBody SysPayRecord record) {
		return HttpResult.ok(sysPayRecordService.save(record));
	}

    /**
     * 删除支付信息表
     * @param records
     * @return
     */
	@PostMapping(value="/delete")
	@ApiOperation(value = "删除支付信息表记录", httpMethod = "POST", notes = "删除支付信息表记录")
	public HttpResult delete(@ApiParam(name="SysPayRecord列表",value="SysPayRecord列表")@RequestBody List<SysPayRecord> records) {
		return HttpResult.ok(sysPayRecordService.delete(records));
	}

    /**
     * 基础分页查询
     * @param pageRequest
     * @return
     */    
	@PostMapping(value="/findPage")
	@ApiOperation(value="支付信息表数据列表", httpMethod = "POST", notes = "获取支付信息表列表")
	public HttpResult findPage(@ApiParam(name="PageRequest",value="查询对象")@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysPayRecordService.findPage(pageRequest));
	}
	
    /**
     * 根据主键查询
     * @param id
     * @return
     */ 	
	@GetMapping(value="/findById")
	@ApiOperation(value="支付信息表数据详情",httpMethod = "GET",notes = "支付信息表数据详情")
	public HttpResult findById(@ApiParam(name="id",value="业务对象主键")@RequestParam Long id) {
		return HttpResult.ok(sysPayRecordService.findById(id));
	}
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */ 	
	@GetMapping(value="/findByCode/{code}")
	@ApiOperation(value="支付信息表数据详情",httpMethod = "GET",notes = "支付信息表数据详情")
	public HttpResult findByCode(@ApiParam(name="code",value="业务对象主键")@RequestParam String code) {
		return HttpResult.ok(sysPayRecordService.findByCode(code));
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
		PageList<SysPayRecord> query = sysPayRecordService.query(queryFilter);
		return HttpResult.ok(query);
	}
	
    /**
     * 验证支付状态
     * @param id
     * @return
     */ 	
	@GetMapping(value="/verifyPayType/{code}")
	@ApiOperation(value="验证支付状态",httpMethod = "GET",notes = "支付信息表数据详情")
	public List getOrderByCode(@ApiParam(name="code",value="业务对象主键",required = true)@PathVariable String code) {
		List b = sysPayRecordService.verifyPayType(code);
		return b;
	}
	
	
}
