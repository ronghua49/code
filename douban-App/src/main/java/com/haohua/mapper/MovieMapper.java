package com.haohua.mapper;    /*
 * @author  Administrator
 * @date 2018/7/11
 */

import com.haohua.entity.Movie;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**对应movieMapper.xml的增删该查接口
 * @author Administrator
 */
public interface MovieMapper {

    @Insert("insert into t_movie (movie_name,director_name,area) values (#{movieName},#{directorName},#{area})")
    void saveMovie(Movie movie);
   Movie findByMovieId(Integer id) ;


    void updateByMovie(Movie movie);



}
