package com.example.test;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ThreadTest {
    @Test
    public void test(){
        Car car = new Car();
        car.start();
    }


    @Test
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[] {map.get(target-nums[i]),i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    @Test
    public void test2() {
        int[] ints = twoSum(new int[]{1,2,4}, 6);
        System.out.println(ints[0]+""+ints[1]);
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
        }
    }

}