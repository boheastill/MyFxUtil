package cn.i623.myspringutil.fun;

import lombok.Data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TestStream {
    public static void main(String[] args) {

        List<Student> partList = new LinkedList<>();
        Collections.addAll(partList, new Student("战国是"), new Student("张三"), new Student("李四xxx"), new Student("介绍"), new Student("产品"), new Student("研发"));
/*        partList.stream().forEach(e -> {
            if ("张三".equals(e.getName())) {
                e = new Student("王五");
            }
        });*/
        List<Student> studentList = partList.stream().peek(e -> {
            if (!"张三".equals(e.getName())) {
                e.setName("王五666");
            }
        }).collect(Collectors.toList());
//        partList = partList.stream().filter(e -> !"张三".equals(e.getName())).collect(Collectors.toList());

        System.out.println(studentList);
//        System.out.println(collect);
    }

    @Data
    static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }
    }
}
