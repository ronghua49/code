package com.haohua.serviceinpl;

import com.haohua.controller.ProductController;
import com.haohua.entity.Product;
import com.haohua.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author haohua
 * @version 1.0
 * @describe
 * @date 2020/1/11 10:15
 */
@Component
public class OrderDeal implements Callable<ConcurrentLinkedQueue<Product>> {

    private static ProductService productService;


    public OrderDeal() {
    }

    public OrderDeal(ProductService productService) {
        this.productService = productService;
    }

    public static ReentrantLock dealLock = new ReentrantLock();
    @Override
    public ConcurrentLinkedQueue<Product> call() throws Exception {
        ConcurrentLinkedQueue<Product> orderQueue = ProductController.productRequestQueue;
            try {
                // 每次允许单个线程执行下单
                boolean b = dealLock.tryLock();
                // 使用迭代器 对象是复制的对象
//                Iterator<Product> iterator = orderQueue.iterator();
//                while (iterator.hasNext()) {
//                    dealOrder(iterator.next());
//                }
                if(b){
                    for (Product product : orderQueue) {
                        if(product.getStatus()==0){
                            dealOrder(product);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                dealLock.unlock();
            }
        return orderQueue;
    }

//    @Override
//    public void run() {
//        ConcurrentLinkedQueue<Product> orderQueue = ProductController.productRequestQueue;
//        while (!orderQueue.isEmpty()) {
//            try {
//                // 每次允许单个线程执行下单
//                dealLock.lock();
//                // 使用迭代器 对象是复制的对象
////                Iterator<Product> iterator = orderQueue.iterator();
////                while (iterator.hasNext()) {
////                    dealOrder(iterator.next());
////                }
//                for (Product product : orderQueue) {
//                    if(product.getStatus()==0){
//                        dealOrder(product);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }finally {
//                dealLock.unlock();
//            }
//        }
//    }

    private void dealOrder(Product next) {
        if (next.getStatus() == 0) {
            int inventory = productService.getInventory(next.getPro_name());
            int status = 2;
            if (inventory > 0) {
                productService.decrementStock(next.getPro_name());
                status = 1;
            }
            if (status == 2) {
                next.setStatus(2);
                System.out.println(next.getUserId() + "库存不足,当前线程名：" + Thread.currentThread().getName());
            } else {
                System.out.println(next.getUserId() + "购买成功，等待发货。线程名：" + Thread.currentThread().getName());
                next.setStatus(1);
            }
        }
    }


}
