package com.haohua.mapper;    /*
 * @author  Administrator
 * @date 2018/12/6
 */

import com.haohua.TMapper;
import com.haohua.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends TMapper<User> {

}
