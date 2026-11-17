package com.itheima;


import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void testThreadLocal(){
        ThreadLocal tl = new ThreadLocal<>();

        new Thread(() -> {
            tl.set("test");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        }, "Thread-1").start();

        new Thread(() -> {
            tl.set("test2");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        }, "Thread-2").start();
    }
}
