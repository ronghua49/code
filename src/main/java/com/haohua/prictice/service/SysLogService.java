package com.haohua.prictice.service;

/**
 * 日志管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysLogService  {
	/**
	 * 
	 * @describe 手动调用写成功日志
	 * @author sjg 
	 * @date 2019年8月1日 下午3:52:51
	 * @version 1.0
	 * @param message
	 */
	public void createSuccessLog(String message);
	/**
	 * 
	 * @describe 手动调用写失败日志
	 * @author sjg 
	 * @date 2019年8月1日 下午3:53:29
	 * @version 1.0
	 * @param message
	 */
	public void createErrorLog(String message);
}
