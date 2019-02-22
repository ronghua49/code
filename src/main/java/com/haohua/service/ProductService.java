package com.haohua.service;    /*
 * @author  Administrator
 * @date 2019/2/20
 */

import com.haohua.Dto.Order;
import com.haohua.exception.BusyException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {


    @Transactional(propagation = Propagation.REQUIRED,isolation =Isolation.READ_COMMITTED)
    Order getOrder();
    @Transactional(propagation = Propagation.REQUIRED,isolation =Isolation.READ_COMMITTED)
    Order getOrder2() throws BusyException;
}
