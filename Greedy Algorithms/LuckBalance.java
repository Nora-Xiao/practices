package com.siemens.slaco.itcompact.controller;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LuckBalance {
    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {
        int totalLuck = 0;
        List<Integer> important = new ArrayList<>();
        for (int[] contest : contests) {
            int luck = contest[0];
            int importance = contest[1];
            if (importance == 0) {
                totalLuck += luck;
            } else {
                important.add(luck);
            }
        }
        Collections.sort(important);
        int number = k;
        if (k > important.size()) {
            number = important.size();
        }

        for (int i = 0; i < important.size() - number; i++) {
            totalLuck -= important.get(i);
        }
        for (int i = important.size() - number; i < important.size(); i++) {
            totalLuck += important.get(i);
        }

        return totalLuck;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
