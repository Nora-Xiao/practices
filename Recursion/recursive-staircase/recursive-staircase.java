import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// https://www.hackerrank.com/challenges/ctci-recursive-staircase/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=recursion-backtracking
public class Solution {
    // key is n, value is stepPerms(n)
    private static Map<Integer, Integer> stored = new HashMap<>();

    // Complete the stepPerms function below.
    static int stepPerms(int n) {
        // when to stop, base case
        if (n == 0) { 
            return 1;
        } else {
            int total = 0;
            // 如果这步走3，后面还有n - 3要走，有几种可能性？
            if (n >= 3) {
                // 单纯这样太慢，有很多重复的recursion
                //total += stepPerms(n - 3);
                int next = n - 3;
                if (!stored.containsKey(next)) {
                    stored.put(next, stepPerms(next));
                }
                total += stored.get(next);
            }
            if (n >= 2) {
                //total += stepPerms(n - 2);
                int next = n - 2;
                if (!stored.containsKey(next)) {
                    stored.put(next, stepPerms(next));
                }
                total += stored.get(next);
            }
            if (n >= 1) {
                //total += stepPerms(n - 1);
                int next = n - 1;
                if (!stored.containsKey(next)) {
                    stored.put(next, stepPerms(next));
                }
                total += stored.get(next);
            }
            return total;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int sItr = 0; sItr < s; sItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int res = stepPerms(n);

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
