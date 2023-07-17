package com.duydv.vn.foodappmvvm.utils;

public class StringUtil {
    public static boolean isEmpty(String input) {
        return input == null || input.isEmpty() || ("").equals(input.trim());
    }
}
