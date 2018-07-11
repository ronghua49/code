package com.haohua.mapper;    /*
 * @author  Administrator
 * @date 2018/7/11
 */

import com.haohua.entity.Movie;

import java.util.List;

public interface MovieMapper {
    List<Movie> findByMovieId(Integer id);

}
