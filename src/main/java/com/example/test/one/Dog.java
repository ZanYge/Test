package com.example.test.one;


import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    String name;
    Integer age;

    public String getName() {
        return name;
    }
    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Dog(String name,Integer age) {
        this.name = name;
        this.age = age;
    }

    public Dog() {

    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                "age='" + age + '\'' +
                '}';
    }



    @Override
    public int compareTo(Dog o) {
        return o.getAge();
    }
}
