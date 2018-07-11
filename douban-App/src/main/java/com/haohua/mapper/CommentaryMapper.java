package com.haohua.mapper;    /*
 * @author  Administrator
 * @date 2018/7/11
 */

import com.haohua.entity.Commentary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentaryMapper {
    List<Commentary> findByCommentaryId(@Param("idList") List<Integer> idList);
    Commentary findByUserId(Integer id);
}
