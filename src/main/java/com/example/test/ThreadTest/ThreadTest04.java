package com.example.test.ThreadTest;

public class ThreadTest04 {
    public static void main(String[] args) throws InterruptedException {
        T5 t5 = new T5();
        t5.start();

        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            System.out.println("小弟吃包子");
            //让老大插队进来
            //t5.join();
            //礼让别人进来
            Thread.yield();
        }
    }
}

class T5 extends  Thread {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("老大吃包子");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

