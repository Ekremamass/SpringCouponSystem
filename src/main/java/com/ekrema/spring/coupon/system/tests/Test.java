package com.ekrema.spring.coupon.system.tests;

public class Test {
    private static int count = 1;

    public static void test(String title) {
        System.out.printf("\n===================== Test %03d - %s\n", count++, title);
    }
}
