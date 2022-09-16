package com.example.test.ThreadTest;

public class ThreadTest03 {
    public static void main(String[] args) throws InterruptedException {
        T4 t4 = new T4();
        Thread t1 = new Thread(t4);
        t1.setName("线程一");
        
        t1.start();
        System.out.println("休眠中");
        Thread.sleep(1000 * 2);
        t4.setRunning(false);
    }
}

class T3 extends  Thread {
    Integer id = new Integer(100);
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("T1 ===="+(--id));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class T4 implements Runnable {
    Integer id = new Integer(100);
    boolean running = true;
    @Override
    public void run() {
        while (running) {
            System.out.println("T2 ===="+(--id));
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
