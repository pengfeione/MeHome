package com.mehome.utils;

import java.util.Random;

public class RandomUtils {
    public static final String pattern = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678";

    public static String password(int len) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] arr = pattern.toCharArray();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(arr[new Random().nextInt(pattern.length())]);
        }
        return stringBuilder.toString();
    }


    public static String random(int len) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] arr = pattern.toCharArray();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(arr[new Random().nextInt(pattern.length())]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(password(6));
    }
}
