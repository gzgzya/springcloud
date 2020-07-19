package com.jinan.springcloud.thread;

import com.jinan.springcloud.entity.User;
import com.jinan.springcloud.mapper.UserMapper;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ImportThread implements Runnable {

    public ImportThread() {
    }

    UserMapper userMapper;
    private List<User> list;
    private CountDownLatch begin;
    private CountDownLatch end;

    /**
     * 方法名: ImportThread
     * 方法描述: 创建个构造函数初始化 list,和其他用到的参数
     * @throws
     */
    public ImportThread(List<User> list, CountDownLatch begin, CountDownLatch end, UserMapper userMapper) {
        this.list = list;
        this.begin = begin;
        this.end = end;
        this.userMapper = userMapper;
    }

    @Override
    public void run() {
        try {
            //执行完让线程直接进入等待
            userMapper.saveBatch(list);
            begin.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //这里要主要了，当一个线程执行完 了计数要减一不然这个线程会被一直挂起
            //这个方法就是直接把计数器减一的
            end.countDown();
        }
    }


}
