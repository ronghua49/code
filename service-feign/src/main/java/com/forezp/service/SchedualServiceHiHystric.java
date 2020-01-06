package com.forezp.service;

import com.forezp.model.Product;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Created by fangzhipeng on 2017/4/6.
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }

    @Override
    public List<Product> getProduct(Product product) {
        return Collections.emptyList();
    }
}
