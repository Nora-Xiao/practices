package com.siemens.slaco.itcompact.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SherlockandAnagrams {
    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        char[] characters = s.toCharArray();
        //Map<Integer[], Integer> frequencies = new HashMap<>();
        Map<String, Integer> frequencies = new HashMap<>();
        for (int start = 0; start < characters.length; start++) {
            // exclude end
            for (int end = start + 1; end < characters.length + 1; end++) {
                // 搞到key们
                // a是97, 要把其变成0，需要-97
                Integer[] key = new Integer[26];
                for (int i = start; i < end; i++) {
                    int index = ((int) characters[i]) - 97;
                    if (key[index] == null) {
                        key[index] = 0;
                    }
                    key[index] += 1;
                }
                String stringKey = "";
                for (int i = 0; i < key.length; i++) {
                    stringKey += key[i];
                }
                System.out.println(stringKey);
                if (frequencies.containsKey(stringKey)) {
                    int thisFrequency = frequencies.get(stringKey);
                    frequencies.put(stringKey, thisFrequency + 1);
                } else { // 没有key
                    frequencies.put(stringKey, 1);
                }
            }
        }

        int total = 0;
        // use combination
        System.out.println(frequencies.size());
        for (String key : frequencies.keySet()) {
            int value = frequencies.get(key);
            System.out.println(value);
            total += value * (value - 1) / 2;
        }
        return total;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);
            System.out.println(result);

            //bufferedWriter.write(String.valueOf(result));
            //bufferedWriter.newLine();
        }

        // bufferedWriter.close();

        scanner.close();
    }
}
