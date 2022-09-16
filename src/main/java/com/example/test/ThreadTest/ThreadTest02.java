package com.example.test.ThreadTest;

public class ThreadTest02 {
    public static void main(String[] args) {
//        T1 t1 = new T1();
//        T1 t2 = new T1();
//        T1 t3 = new T1();
//        t1.start();
//        t2.start();
//        t3.start();

        T2 t4 = new T2();
        new Thread(t4).start();
        new Thread(t4).start();
        new Thread(t4).start();
    }
}

class T1 extends  Thread {
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

class T2 implements Runnable {
    Integer id = new Integer(100);
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("T2 ===="+(--id));
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
