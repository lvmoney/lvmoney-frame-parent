package com.lvmoney.demo.bitmap.test;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

public class Test {
    public static void main(String[] args) {
        BloomFilter<String> filter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000,0.00001);

        filter.put("A");
        filter.put("B");
        filter.put("C");

        if(filter.mightContain("A")){
            System.err.println("Has Exist A");
        }else{
            System.err.println("No Exist A");
        }


        if(filter.mightContain("D")){
            System.err.println("Has Exist D");
        }else{
            System.err.println("No  Exist D");

        }
        System.out.println(filter.negate().toString());
    }
}
