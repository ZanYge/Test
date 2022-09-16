package com.example.test.one;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Test1 {
    public static void main(String[] args) {
        List<Dog> dogList = new ArrayList<>();
        List<String> dogNameList = new ArrayList<>();
        if (dogList != null && dogList.size() > 0) {
            for (int i = 0; i < dogList.size(); i++) {
                Dog dog = dogList.get(i);
                if (dog != null) {
                    dogNameList.add(dog.getName());
                }
            }
        }
        System.out.println(dogNameList);
        System.out.println("你和啊啊啊");
        System.out.println("你和啊啊啊");
    }
}
