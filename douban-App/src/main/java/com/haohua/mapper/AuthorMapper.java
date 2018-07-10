package com.haohua.mapper;    /*
 * @author  Administrator
 * @date 2018/7/10
 */

import com.haohua.entity.Author;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorMapper {
    void insertAuthor(Author author);
    void deleteById(Integer id);
    void updateById(Author author);
    Author  selectById(Integer id);
    List<Author> selectAll(String key);
    List<Author> findByPage(@Param("start") Integer start,@Param("pageSize") Integer Pagesize);
}
