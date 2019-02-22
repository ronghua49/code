package com.haohua.serviceinpl;    /*
 * @author  Administrator
 * @date 2019/2/20
 */

import com.haohua.Dto.Order;
import com.haohua.entity.Product;
import com.haohua.exception.BusyException;
import com.haohua.mapper.ProductMapper;
import com.haohua.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Order getOrder() {

        Product product = productMapper.selectByPrimaryKey(1);

        if (product.getInventory() > 0) {
            product.setInventory(product.getInventory() - 1);
            productMapper.updateByPrimaryKeySelective(product);
            Order order = new Order();
            order.setProName(product.getPro_name());
            order.setProPrice(product.getPro_price());
            return order;

        } else {
            return null;
        }
    }

    @Override
    public Order getOrder2() throws BusyException {
        Product product = productMapper.selectByPrimaryKey(1);
        if (product.getInventory() > 0) {
            int i = productMapper.updateById(product.getId(),product.getVersion());
            if (i > 0) {
                Order order = new Order();
                order.setProName(product.getPro_name());
                order.setProPrice(product.getPro_price());
                return order;
            } else {
                throw new BusyException();
            }

        } else {
            return null;
        }
    }

}
