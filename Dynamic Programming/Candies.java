package com.siemens.slaco.itcompact.controller;

import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.regex.*;

public class Candies {

    // Complete the candies function below.
    static long candies(int n, int[] arr) {
        // 变大，变小，相等，互相接在一起
        // 1 2 3 1 1 1
        // 1 2 3 2 1
        // 1 2 3 3 3 3
        // 1 1 1 2 3
        // 3 3 1 2 3
        // 3 2 1 1 2 3

        // 先从左到右过一遍，找到连续上升的，如1 2 3
        int[] inc = new int[n];
        // 无论开头是连续上升，还是连续下降，还是等于，第一个都是1
        // 同时为inc[1]有可能= inc[0] + 1做准备
        inc[0] = 1;
        for (int i = 1; i < n; i++) {
            // 连续上升
            if (arr[i] > arr[i - 1]) {
                inc[i] = inc[i - 1] + 1;
            } else {
                inc[i] = 1;
            }
        }

        // 再从右到左过一遍，找到连续下降的，如3 2 1
        int[] dec = new int[n];
        dec[n - 1] = 1;
        for (int j = n - 2; j >= 0; j--) {
            // 连续下降
            if (arr[j] > arr[j + 1]) {
                dec[j] = dec[j + 1] + 1;
            } else {
                dec[j] = 1;
            }
        }

        long candies = 0;
        for (int k = 0; k < n; k++) {
            candies += Math.max(inc[k], dec[k]);
        }

        return candies;

        /*
        long[] saved = new long[n];
        // 第一个element必然+1
        long candies = 1;
        saved[0] = 1;
        if (n >= 2) {
            // 俩一样大，+1
            if (arr[0] == arr[1]) {
                candies++;
                saved[1] = 1;
            } else { // 一大一小，+2
                candies += 2;
                saved[1] = 2;
            }
        }

        if (n >= 3) {
            // 从第3个element开始
            for (int i = 2; i < n; i++) {
                if (saved[i - 1] == 1) {
                    if (arr[i - 1] != arr[i]) {
                        saved[i] = 2;
                    } else {
                        saved[i] = 1;
                    }
                } else {
                    int oneBefore = arr[i - 1];
                    int twoBefore = arr[i - 2];
                    boolean increase = (twoBefore < oneBefore) && (oneBefore < arr[i]);
                    boolean decrease = (twoBefore > oneBefore) && (oneBefore > arr[i]);
                    boolean equalThenIncrease = (twoBefore == oneBefore) && (oneBefore < arr[i]);
                    boolean equalThenDecrease = (twoBefore == oneBefore) && (oneBefore > arr[i]);
                    if (increase || decrease || equalThenIncrease || equalThenDecrease) {
                        saved[i] = saved[i - 1] + 1;
                    } else {
                        saved[i] = 1;
                    }
                }
                candies += saved[i];

                /*
                int oneBefore = arr[i - 1];
                int twoBefore = arr[i - 2];
                boolean increase = (twoBefore < oneBefore) && (oneBefore < arr[i]);
                boolean decrease = (twoBefore > oneBefore) && (oneBefore > arr[i]);
                boolean equalThenIncrease = (twoBefore == oneBefore) && (oneBefore < arr[i]);
                if (increase || decrease || equalThenIncrease) {
                    long thisCandy = saved[i - 1] + 1;
                    candies += thisCandy;
                    saved[i] = thisCandy;
                } else {
                    candies++;
                    saved[i] = 1;
                }

            }
        }

        return candies;
        */
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FileDescriptor.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

