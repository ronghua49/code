package com.haohua.prictice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

/**
 * ---------------------------
 * 微服务实体类属性信息 (LoginInfo)         
 * ---------------------------
 * 作者：  宋建国
 * @date 2019-04-30 17:23:11
 * @since jdk8
 * ---------------------------
 */
@ApiModel(value = "LoginInfo",description = "登录员工实体类") 
public class LoginInfo {
	private static final long serialVersionUID = 1L;
	public enum LoginUserType{
		SYSUSER,BRANCHUSER,APPUSER
	};
	@ApiModelProperty(value="员工ID")
	private String userid;
	@ApiModelProperty(value="员工账号")
	private String username;
	@ApiModelProperty(value="token")
	private String token;
	@ApiModelProperty(value="员工姓名")
	private String empname;
	@ApiModelProperty(value="员工信息")
	private Object user;	
	@ApiModelProperty(value="接口权限")
	private Set<String> permissions;	
	@ApiModelProperty(value="用户类型")
	private LoginUserType loginUserType;
	@ApiModelProperty(value="岗位ID")
	private Long postId;
	@ApiModelProperty(value="聊天ID")
	private Long chatId;
	
	public Long getChatId() {
		return chatId;
	}
	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Object getUser() {
		return user;
	}
	public void setUser(Object user) {
		this.user = user;
	}
	public Set<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}
	public LoginUserType getLoginUserType() {
		return loginUserType;
	}
	public void setLoginUserType(LoginUserType loginUserType) {
		this.loginUserType = loginUserType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
