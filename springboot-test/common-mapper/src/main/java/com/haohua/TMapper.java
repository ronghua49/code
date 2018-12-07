package com.haohua;    /*
 * @author  Administrator
 * @date 2018/12/6
 */

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface TMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
