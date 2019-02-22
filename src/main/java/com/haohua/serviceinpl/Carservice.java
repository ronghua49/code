package com.haohua.serviceinpl;    /*
 * @author  Administrator
 * @date 2018/10/20
 */

import com.haohua.entity.Car;
import com.haohua.mapper.CarMapper;
import com.haohua.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
class CarserviceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;
    @Override
    public List<Car> findAllCar(){
        return  carMapper.selectByExample(null);
    }


}
