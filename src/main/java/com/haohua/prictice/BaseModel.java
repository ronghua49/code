package com.haohua.prictice;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础模型
 * @author Louis
 * @date Sep 13, 2018
 */
public class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="编码")
	private Long id;
	@ApiModelProperty(value="创建人姓名",hidden=true)
    private String createBy;
	@ApiModelProperty(value="创建时间",hidden=true)
    private Date createTime;
	@ApiModelProperty(value="最后修改人名称",hidden=true)
    private String lastUpdateBy;
	@ApiModelProperty(value="最后修改时间",hidden=true)
    private Date lastUpdateTime;
	@ApiModelProperty(value="岗位ID",hidden=true)
    private Long postId;

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
