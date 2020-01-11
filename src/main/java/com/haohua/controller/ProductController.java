package com.haohua.controller;    /*
 * @author  Administrator
 * @date 2019/2/20
 */

import com.haohua.Dto.Order;
import com.haohua.entity.Product;
import com.haohua.exception.BusyException;
import com.haohua.exception.LackOfGoodsException;
import com.haohua.service.ProductService;
import com.haohua.serviceinpl.OrderDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // 创建请求队列
    public static volatile ConcurrentLinkedQueue<Product> productRequestQueue = new ConcurrentLinkedQueue<>();
    // 创建线程池
    private ExecutorService executorService = Executors.newCachedThreadPool();
    // 定义排队请求最大数量需要和商品总数相同
    private static AtomicInteger totalRequestQueue = new AtomicInteger(100);


    /**
     * @Description: 高并发下单方法测试
     * @Param:
     * @return:
     * @Author: 荣浩华
     * @Date: 2020/1/11
     */
    @PostMapping("/order")
    public String orderPro(@RequestBody Product product) throws ExecutionException, InterruptedException {
        String pro_name = product.getPro_name();
        int stock = productService.getInventory(pro_name);
        if (stock < 1) {
            return "库存不足 " + product.getUserId();
        } else {
            if (productRequestQueue.size() < totalRequestQueue.get()) {
                productRequestQueue.add(product);
                System.out.println(product.getUserId() + "增加到待处理队列成功" + productRequestQueue.size());
            } else {
                return "队列已满" + product.getId();
            }
        }
        if (!OrderDeal.dealLock.isLocked()) {
            OrderDeal orderDeal = new OrderDeal(productService);
            Future<ConcurrentLinkedQueue<Product>> submit = executorService.submit(orderDeal);
            ConcurrentLinkedQueue<Product> products = submit.get();
            for (Product product1 : submit.get()) {
                if (product.getUserId() == product1.getUserId()) {
                    if (product.getStatus() == 1) {
                        return "恭喜用户" + product.getUserId() + "购买成功！";
                    } else {
                        return "购买失败" + product.getUserId() + "请再尝试！";
                    }
                }
            }
        }

        throw new RuntimeException("服务器错误");
    }

    /**
     * @Description: 正常不用多线程处理下单情况 测试
     * @Param:
     * @return:
     * @Author: 荣浩华
     * @Date: 2020/1/11
     */
    @PostMapping("/order2")
    public String orderPro2(@RequestBody Product product) throws InterruptedException {
        int stock = productService.getInventory(product.getPro_name());
        if (stock < 5) {
            return "对不起" + product.getUserId() + "库存不足";
        }
        // 减少库存
        productService.decrementStock(product.getPro_name());
        Thread.yield();
        int stock2 = productService.getInventory(product.getPro_name());
        System.out.println("当前库存：" + stock2);
        return "恭喜用户" + product.getUserId() + "购买成功！";
    }

    /**
     * 悲观锁，单线程访问，线程同步(或者用数据库锁也可以实现)
     *
     * @return
     */
    @GetMapping("/huawei")
    public synchronized Order qianggou() {
        Order order = productService.getOrder();
        if (order != null) {
            return order;
        } else {
            throw new LackOfGoodsException();
        }
    }

    /**
     * 乐观锁实现
     *
     * @return
     */
    @GetMapping("/huawei2")
    public Order qianggou2() throws BusyException {

        Order order = productService.getOrder2();
        if (order != null) {
            return order;
        } else {
            throw new LackOfGoodsException();
        }
    }

}
