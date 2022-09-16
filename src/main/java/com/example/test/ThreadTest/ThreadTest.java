package com.example.test.ThreadTest;

public class ThreadTest {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
    }
}

class Car extends Thread{
    Integer id = new Integer(0);
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("小猫睡觉了"+(++id));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(id==3){
                return;
            }
        }

    }

}
