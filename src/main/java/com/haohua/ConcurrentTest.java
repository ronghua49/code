package com.haohua;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.common.util.concurrent.RateLimiter;

/**
 * 高并发流量控制测试
 */
public class ConcurrentTest {
    private static RateLimiter rateLimiter = RateLimiter.create(8);
    private static AtomicInteger suc = new AtomicInteger(0), fail = new AtomicInteger(0);

    // 模拟20个商品
    private static AtomicInteger stock = new AtomicInteger(20);

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub

        List<Runnable> tasks = new ArrayList<Runnable>();
        for (int i = 0; i < 100; i++) {
            tasks.add(new UserRequest(i));
        }
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (Runnable runnable : tasks) {
            // 模拟高并发，如果不设置休眠，相当于100个请求完全并发
            Thread.sleep(100);
            threadPool.execute(runnable);
        }


    }


    /**
     * @Description: 使用令牌桶算法平滑请求速率，控制网络高并问题。如果当前线程执行 令牌桶中存在令牌，则允许发送流量，否则快速返回失败。线程执行完 令牌会回入桶内。
     * @Param:
     * @return:
     * @Author: 荣浩华
     * @Date: 2020/1/9
     */
    private static boolean startGo(int i) {
        //基于令牌桶算法的限流实现类

        /**
         * 一秒出10个令牌，0.1秒出一个，100个请求进来，假如100个是同时到达， 那么最终只能成交10个，90个都会因为超时而失败。
         *
         */

        /**
         * tryAcquire(long timeout, TimeUnit unit)
         * 从RateLimiter 获取许可如果该许可可以在不超过timeout的时间内获取得到的话，
         * 或者如果无法在timeout 过期之前获取得到许可的话，那么立即返回false（无需等待）
         */

        //判断能否在1秒内得到令牌，如果不能则立即返回false，不会阻塞程序
        if (!rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
            System.out.println("暂时无法获取令牌， 排队失败线程" + i);
            fail.getAndIncrement();
            System.out.println("SUC/FAIL=" + suc.get() + "/" + fail.get());
            return false;
        }
        if (stock.get() > 0) {
            System.out.println("成功" + i);
            suc.getAndIncrement();
            System.out.println("FAIL/SUC=" + fail.get() + "/" + suc.get());
            update();
            return true;
        }

        System.out.println("数据不足，失败");
        return false;
    }


    // 更新库存
    private static int update() {
        return stock.getAndDecrement();
    }

    private static class UserRequest implements Runnable {
        private int id;

        public UserRequest(int id) {
            this.id = id;
        }

        public void run() {
            startGo(id);
        }
    }

}











































