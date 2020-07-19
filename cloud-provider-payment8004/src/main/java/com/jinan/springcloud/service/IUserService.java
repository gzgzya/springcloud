package com.jinan.springcloud.service;

import com.jinan.springcloud.entity.User;
import com.jinan.springcloud.mapper.UserMapper;
import com.jinan.springcloud.thread.ImportThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Service
public class IUserService {


    @Autowired
    private UserMapper userMapper;



    public static void saveBatch(List<User> list) throws Exception {
        //一个线程处理200条数据
        int count = 200;
        //数据集合大小
        int listSize = list.size();
        //开启的线程数
        int runSize = (listSize / count) + 1;
        //存放每个线程的执行数据
        List<User> newlist = null;

        //创建一个线程池，数量和开启线程的数量一样
        //Executors 的写法
        // ExecutorService executor = Executors.newFixedThreadPool(runSize);

        //ThreadPoolExecutor的写法
        ThreadPoolExecutor executor = new ThreadPoolExecutor(runSize, runSize, 1,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        //创建两个个计数器
        CountDownLatch begin = new CountDownLatch(1);
        Executors.newScheduledThreadPool()
        CountDownLatch end = new CountDownLatch(runSize);
        //循环创建线程
        for (int i = 0; i < runSize; i++) {
            //计算每个线程执行的数据
            if ((i + 1) == runSize) {
                int startIndex = (i * count);
                int endIndex = list.size();
                newlist = list.subList(startIndex, endIndex);
            } else {
                int startIndex = (i * count);
                int endIndex = (i + 1) * count;
                newlist = list.subList(startIndex, endIndex);
            }
            //线程类
            ImportThread mythead = new ImportThread(newlist, begin, end,userMapper);
            //这里执行线程的方式是调用线程池里的executor.execute(mythead)方法。
            executor.execute(mythead);
        }
        begin.countDown();
        end.await();
        //执行完关闭线程池
        executor.shutdown();
    }


}
