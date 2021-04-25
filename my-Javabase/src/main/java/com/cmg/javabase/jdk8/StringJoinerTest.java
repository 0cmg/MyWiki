package com.cmg.javabase.jdk8;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.IntStream;

/**
 * @CLassName StringJoinerTest
 * @Description TODO
 * @Author cmg
 * @Date 2021/4/20 8:57
 * @Version 1.1
 **/
public class StringJoinerTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(1,10).forEach(i->{
            sb.append(i+"");
            if( i < 10){
                sb.append(",");
            }
        });
        System.out.println(sb);


        StringJoiner sj = new StringJoiner(",");
        IntStream.range(1,10).forEach(i->sj.add(i+""));
        System.out.println(sj);

//        Objects.requireNonNull(null, "The prefix must not be null");


        System.out.println(Objects.deepEquals(sb, sj));
    }
}
