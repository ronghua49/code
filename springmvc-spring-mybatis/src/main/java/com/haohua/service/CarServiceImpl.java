package com.haohua.service;    /*
 * @author  Administrator
 * @date 2018/7/23
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haohua.entity.Parts;
import com.haohua.entity.PartsExample;
import com.haohua.entity.Type;
import com.haohua.entity.TypeExample;
import com.haohua.exception.DatasourceAccessException;
import com.haohua.exception.NotFoundException;
import com.haohua.mapper.PartsMapper;
import com.haohua.mapper.TypeMapper;
import com.haohua.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;

/**
 * 汽车服务 业务处理层
 * @author ronghua
 */
@Service
public class CarServiceImpl implements CarService{
   @Autowired
    private PartsMapper partsMapper;
   @Autowired
   private TypeMapper typeMapper;

    @Override
    public Parts findById(Integer id)  {
        Parts parts = partsMapper.selectByPrimaryKey(id);
        if (parts!=null){
             return parts;
        }else {
            throw new NotFoundException("未找到零部件");
        }
    }

    /**
     * 根据页码和map参数查询 part的分页对象
     *
     * @param pageNo 当前页码
     * @param paramMap 查询part对象的条件
     * @return part的page对象
     */
    @Override
    public PageInfo<Parts> findWithTypeByPageNoAndMap(Integer pageNo, Map<String, Object> paramMap) {
            //在执行sql前告诉分页条件
        PageHelper.startPage(pageNo,Constant.DEFAULT_PAGESIZA);
            //根据条件查询part的集合
        List<Parts> partsList = partsMapper.selectWithTypeByParamMap(paramMap);
            //把分页好的对象集合封装成PageInfo对象
        PageInfo<Parts> pageInfo = new PageInfo<>(partsList);
        return pageInfo;
    }

    /**
     * 查询所有的type对象
     *
     * @return 返回type对象集合
     */
    @Override
    public List<Type> findTypeList() {
        List<Type> typeList = typeMapper.selectByExample(null);
        return typeList;
    }

    /**
     * 根据表单提交的part对象 新增part
     *
     * @param parts 封装的part对象
     * @return 受影响的行数
     */
    @Override
    public Integer addNewParts(Parts parts) {
        Integer res = partsMapper.insertSelective(parts);
        return res;
    }

    /**
     * 根据partsId删除parts
     *
     * @return 受影响的行数
     */
    @Override
    public Integer delPartsById(Integer partsId) {
        Integer res = partsMapper.deleteByPrimaryKey(partsId);
        return res;
    }

    /**
     * 根据表单值封装的part对象修改 part
     *
     * @param parts 要修改的对象
     * @return
     */
    @Override
    public Integer editPartsByParts(Parts parts) {
        int res =partsMapper.updateByPrimaryKeySelective(parts);
        return res;
    }

    /**
     * 查找type的对象 并分页
     *
     * @return typelist的page对象
     */
    @Override
    public PageInfo<Type> findTypePage(Integer p) {
        PageHelper.startPage(p,Constant.DEFAULT_PAGESIZA);
        List<Type> typeList = findTypeList();
        return new PageInfo<>(typeList);
    }

    /**
     * 根据typeId 删除type
     *
     * @param typeId
     * @return 受影响的行数
     */
    @Override
    public Integer delTypeById(Integer typeId) {
        return typeMapper.deleteByPrimaryKey(typeId);
    }

    /**
     * 根据类型名删除对应类型
     * @param delName
     * @return
     */
    @Override
    public Integer delTypeByTypeName(String delName) {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andTypeNameEqualTo(delName);
        return typeMapper.deleteByExample(typeExample);
    }

    /**
     * 根据类型名查找类型
     *
     * @param delName
     * @return
     */
    @Override
    public List<Type> findTypeByTypeName(String delName) {
        TypeExample typeExample = new TypeExample();
        typeExample.createCriteria().andTypeNameEqualTo(delName);
        return typeMapper.selectByExample(typeExample);
}

    /**
     * 根据类型id查找partsList集合
     *
     * @param id 类型id
     * @return
     */
    @Override
    public List<Parts> findByTypeId(Integer id) {
        PartsExample partsExample = new PartsExample();
        partsExample.createCriteria().andTypeIdEqualTo(id);
        return partsMapper.selectByExample(partsExample);
    }

    /**
     * 根据类型名新增类型
     *
     * @param addName 新增的类型名
     * @return
     */
    @Override
    public Integer addTypeByTypeName(String addName) {
            Type type = new Type();
            type.setTypeName(addName);
            return typeMapper.insert(type);
    }

    /**
     * 根据类型修改 类型
     *
     * @param type
     * @return
     */
    @Override
    public Integer editTypeByType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }


}
