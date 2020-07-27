package com.siemens.slaco.itcompact.controller;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Roads {

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        // 准备hashmap, 相当于tree的存在
        // roads:
        // 1 2
        // 2 4
        // map:
        // 1 2
        // 2 1 4
        // 4 2
        HashMap<Integer, List<Integer>> t = new HashMap<>();
        for (int i = 0; i < cities.length; i++) {
            int[] row = cities[i];
            int first = row[0];
            int second = row[1];

            // first和second分别当key
            if (!t.containsKey(first)) {
                t.put(first, new ArrayList());
            }
            if (!t.containsKey(second)) {
                t.put(second, new ArrayList());
            }
            // 一条road, 往map里放2次
            List<Integer> l1 = t.get(first);
            l1.add(second);
            List<Integer> l2 = t.get(second);
            l2.add(first);
        }

        long c = 0;
        int citiesFound = 0;
        while (!t.isEmpty()) {
            Set<Integer> keys = t.keySet();
            Iterator<Integer> i = keys.iterator();
            int startingKey = i.next();
            // 1 = startingKey所代表的city
            // 此key + explore此key得到的值
            int citiesCount = 1 + helper(t, startingKey);
            citiesFound += citiesCount;
            // n个lib
            if (c_lib <= c_road) {
                c += c_lib * (long) citiesCount;
            } else { // 1个lib + (n - 1)个road
                c += c_lib + c_road * (long) (citiesCount - 1);
            }
        }

        // 一个city一个group的情况，这个city没有road，因此没进入hashmap
        long leftOver = (long) (n - citiesFound) * c_lib;
        return c + leftOver;
    }

    private static int helper(HashMap<Integer, List<Integer>> t, int startingKey) {
        List<Integer> l = t.get(startingKey);
        // 一旦拿到list，就delete key
        t.remove(startingKey);
        int countValues = 0;
        // 不行：第一层不算，第二层not exist key，才return 0
        // 可以：第一层算，若not exist key，则不explore
        for (int i = 0; i < l.size(); i++) {
            int city = l.get(i);
            if (t.containsKey(city)) {
                // 此value + explore此value得到的值
                countValues += 1 + helper(t, city);
            }
        }
        return countValues;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FileDescriptor.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

