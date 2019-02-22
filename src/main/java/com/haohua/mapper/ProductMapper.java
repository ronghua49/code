package com.haohua.mapper;

import com.haohua.entity.Product;
import com.haohua.entity.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper {
    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    long countByExample(ProductExample example);

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    int deleteByExample(ProductExample example);

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    int insert(Product record);

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    int insertSelective(Product record);

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    List<Product> selectByExample(ProductExample example);

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    Product selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     *
     * @mbg.generated Wed Feb 20 20:43:17 CST 2019
     */
    int updateByPrimaryKey(Product record);

    /**
     * 乐观锁mysql 简单语句实现
     * @param id
     * @param version
     * @return
     */
    int updateById(@Param("id") Integer id,@Param("version") Long version);
}