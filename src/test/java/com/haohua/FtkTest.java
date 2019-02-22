package com.haohua;    /*
 * @author  Administrator
 * @date 2019/1/29
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FtkTest {

    private static int client = 10;

    private static  int threadNum = 3;
    long totalTime = 0;


    public static void main(String[] args) {
        new FtkTest().run();

    }


    public void run(){
        //建立线程池，5个线程可以访问
        ExecutorService exec = Executors.newFixedThreadPool(threadNum);
        //模拟500个用户发起请求
        final CountDownLatch doneSignal = new CountDownLatch(client);

        for(int i=0;i<client;i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    long startTime = System.currentTimeMillis();

                    try {
                        URLConnection connection = new URL("http://localhost:8080/huawei2").openConnection();
                        /* begin获取响应码 */
                        HttpURLConnection httpUrlConnection = (HttpURLConnection)connection;
                        httpUrlConnection.setConnectTimeout(300000);
                        httpUrlConnection.setReadTimeout(300000);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        String result="";
                        while ((inputLine = bufferedReader.readLine()) != null){
                             result  +=inputLine;
                        }
                        System.out.print("result===" + result+"\t\t");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //计数器减一
                    doneSignal.countDown();
                    long endTime = System.currentTimeMillis();
                    System.out.println("线程用时：  "+(endTime-startTime));

                    totalTime += (endTime-startTime);
                }
            };
            exec.execute(runnable);
        }

        try {
            //等到计数器为0 ，阻塞的线程并发执行。否则所有线程阻塞等待。
            doneSignal.await();
            exec.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(totalTime);
    }
}
