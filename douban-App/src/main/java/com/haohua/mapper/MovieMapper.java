package com.haohua.mapper;    /*
 * @author  Administrator
 * @date 2018/7/11
 */

import com.haohua.entity.Movie;

import java.util.List;

/**对应movieMapper.xml的增删该查接口
 * @author Administrator
 */
public interface MovieMapper {
   Movie findByMovieId(Integer id) ;
    void updateByMovie(Movie movie);
}
