package com.example.test;

import com.example.test.one.Dog;
import com.example.test.one.service.AcanthopanaxInterface;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class TestApplicationTests {

    @Test
    void contextLoads() {
        AcanthopanaxInterface acanthopanaxInterface = new AcanthopanaxInterface() {
            @Override
            public List<Dog> get() {
                List<Dog> list = new ArrayList<>();
                list.add(new Dog("张三",1));
                list.add(new Dog("李四",2));
                list.add(new Dog("王五",3));
                list.add(new Dog("赵六",4));
                return list;
            }
        };
        List<Dog> list = acanthopanaxInterface.get();
        Stream<Dog> stream = list.stream();
        //串行
        Stream<Dog> dogStream = list.parallelStream();
        //并行

        /**
         *  1、筛选与切片
         *  filter 从流中排除一些数据 接收lambda
         *  limit 截断流元素不超过指定数量
         *  skip  过滤指定数量
         */
        System.out.println("筛选====>");
        list.stream().filter(dog -> !dog.getName().equals("张三")).forEach(dog -> System.out.println(dog.getName()));
        System.out.println("指定数量====>");
        list.stream().limit(3).forEach(System.out::println);
        System.out.println("过滤====>");
        list.stream().skip(3).forEach(System.out::println);
        System.out.println("筛选 通过hashcode 和 equals 重写去除重复元素====>");
        list.add(new Dog("陈七",7));
        list.add(new Dog("陈七",7));
        list.add(new Dog("陈七",7));
        list.stream().distinct().forEach(System.out::println);
    }
    @Test
    void test1(){
        /**
         * 2、映射
         */
        List<String> list1 = new ArrayList<>();
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(new Function<String, Object>() {
            @Override
            public Object apply(String map) {
                return map.toUpperCase(Locale.ROOT);
            }
        }).forEach(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                list1.add((String) o);
            }
        });
        System.out.println(list);
        System.out.println(list1);

        System.out.println();

        AcanthopanaxInterface acanthopanaxInterface = new AcanthopanaxInterface() {
            @Override
            public List<Dog> get() {
                List<Dog> list = new ArrayList<>();
                list.add(new Dog("张三",1));
                list.add(new Dog("李四",2));
                list.add(new Dog("王五",3));
                list.add(new Dog("赵六",4));
                return list;
            }
        };
        List<Dog> list2 = acanthopanaxInterface.get();
        List<String> list3 = new ArrayList<>();
        list2.stream().filter(s -> s.getName().length() > 3).map(m -> m.getName()).forEach(a ->list3.add(a));

//        Stream<String> stringStream = list2.stream().map(map -> map.getName());
//        stringStream.filter(s -> s.length() < 3).forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                list3.add(s);
//            }
//        });
        System.out.println("list3 == >");
        System.out.println(list3);

        List<Dog> list4 = new ArrayList<>();

        list2.stream().map(new Function<Dog, Object>() {
            @Override
            public Object apply(Dog dog) {
                if (dog.getName().length() < 3) return  dog;
                return null;
            }
        }).forEach(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                list4.add((Dog) o);
            }
        });
        System.out.println(list4);
        System.out.println("=====================");
        List<Dog> listname = acanthopanaxInterface.get();
        List ab = new ArrayList();
        Stream<Stream<Character>> streamStream = listname.stream().map(s -> formString(s.getName()));
        streamStream.forEach(s -> s.forEach(a -> ab.add(a)));
        System.out.println(ab);

        System.out.println("============");
        listname.stream().flatMap(s ->formString(s.getName())).forEach(s ->ab.add(s));
        System.out.println(ab);
    }



    public static Stream<Character> formString(String str){
        ArrayList<Character> list = new ArrayList();
        for (Character cc : str.toCharArray()) {
            list.add(cc);
        }
        return list.stream();
    }


    @Test
    void test2(){
        /**
         * 3、排序
         */
//        List<Integer> integers = Arrays.asList(2, 33, 55, 77, 0, -1, 2);
//        integers.stream().sorted().forEach(System.out::println);

        AcanthopanaxInterface acanthopanaxInterface = new AcanthopanaxInterface() {
            @Override
            public List<Dog> get() {
                List<Dog> list = new ArrayList<>();
                list.add(new Dog("张三",1));
                list.add(new Dog("李四试试",99));
                list.add(new Dog("王五",99));
                list.add(new Dog("赵六",4));
                return list;
            }
        };

        //报错没有实现Comparable接口
//        List<Dog> list = acanthopanaxInterface.get();
//        list.stream().sorted().forEach(System.out::println);

        List<Dog> list = acanthopanaxInterface.get();
        list.stream().sorted(new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                int i = 0;
                if (o1.getAge() - o2.getAge() == 0){
                    i = o1.getName().length() - o2.getName().length();
                }else {
                    i = o1.getAge() - o2.getAge();
                }
                return -i;
            }
        }).forEach(System.out::println);

        list.stream().map(s -> s.getAge()).sorted().forEach(System.out::println);

    }

    @Test
    void test3(){
        /**
         * 4、终止操作
         * 4、1、匹配与查找
         */
        AcanthopanaxInterface acanthopanaxInterface = new AcanthopanaxInterface() {
            @Override
            public List<Dog> get() {
                List<Dog> list = new ArrayList<>();
                list.add(new Dog("张三",19));
                list.add(new Dog("李四试试",99));
                list.add(new Dog("王五",99));
                list.add(new Dog("赵六",28));
                return list;
            }
        };
        List<Dog> list = acanthopanaxInterface.get();
        // allMatch检查是否匹配所有元素 返回Boolean值
        boolean b = list.stream().map((Function<Dog, Object>) dog -> dog.getAge()).allMatch(obj -> (int) obj > 18);
        boolean a = list.stream().allMatch(obj -> obj.getAge() > 18);
        System.out.println(b);
        System.out.println(a);

        System.out.println();

        boolean b1 = list.stream().anyMatch(dog -> dog.getName().length() > 4);
        System.out.println(b1);

        System.out.println();

        boolean b2 = list.stream().noneMatch(dog -> dog.getName().startsWith("雷"));
        System.out.println(b2);

        System.out.println();

        Optional<Dog> first = list.stream().findFirst();
        System.out.println(first.get());

        System.out.println();

        Optional<Dog> any = list.stream().findAny();
        System.out.println(any.get());

        System.out.println();

        long count = list.stream().count();
        System.out.println(count);

        Optional<Integer> max = list.stream().map(s -> s.getAge()).max(Integer::compare);
        System.out.println(max);
        Optional<Dog> min = list.stream().min((o1, o2) -> o1.getAge() - o2.getAge());
        System.out.println(min.get());
        Optional<Dog> first1 = list.stream().sorted((o1, o2) -> -(o1.getAge() - o2.getAge())).findFirst();
        System.out.println(first1.get());

    }
    @Test
    void test4(){
        //归约
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Integer reduce = integers.stream().reduce(0, (integer, integer2) -> integer + integer2);
        System.out.println(reduce);

        AcanthopanaxInterface acanthopanaxInterface = new AcanthopanaxInterface() {
            @Override
            public List<Dog> get() {
                List<Dog> list = new ArrayList<>();
                list.add(new Dog("张三",1));
                list.add(new Dog("李四试试",2));
                list.add(new Dog("王五",3));
                list.add(new Dog("赵六",4));
                return list;
            }
        };
        List<Dog> list = acanthopanaxInterface.get();

        Stream<Integer> integerStream = list.stream().map(Dog::getAge);
        Optional<Integer> reduce1 = integerStream.reduce(Integer::sum);
        Integer integer = 0;
        if (reduce1.isPresent()) {
            integer = reduce1.get();
        }

        reduce1 = null;
        Integer integer1 = reduce1.orElseGet(() -> 1);

        System.out.println(integer1);
    }

    @Test
    void test5(){
        //收集
        AcanthopanaxInterface acanthopanaxInterface = new AcanthopanaxInterface() {
            @Override
            public List<Dog> get() {
                List<Dog> list = new ArrayList<>();
                list.add(new Dog("张三",1));
                list.add(new Dog("李四试试",2));
                list.add(new Dog("王五",3));
                list.add(new Dog("赵六",4));
                return list;
            }
        };
        List<Dog> list = acanthopanaxInterface.get();
        Map<String, Integer> collect = list.stream().collect(Collectors.toMap(dog -> dog.getName(), Dog::getAge));
        System.out.println(collect);
        Integer a = collect.get("李四试试");
        System.out.println(a);

    }

    @Test
    void test6(){
        Dog dog = new Dog("张三",1);
        String name = getName(dog);
        System.out.println(name);

    }

    public String getName(Dog dog){
        //将传入Dog转为Optional对象
        Optional<Dog> dog1 = Optional.ofNullable(dog);
        //如果是空值，设置一个备胎
        Dog dog3 = dog1.orElse(new Dog("无名", 0));
        //返回的还是Dog的名字
        return dog3.getName();
    }




    @Test
    void oo(){
        double d1 = 35.444;
        double d2 = 100;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        // 设置保留几位小数，这里设置的是保留两位小数
        percentInstance.setMinimumFractionDigits(2);
        String format = percentInstance.format(new BigDecimal(d1).divide(new BigDecimal(d2), 4, BigDecimal.ROUND_HALF_UP));
        System.out.println(format);
    }

}
