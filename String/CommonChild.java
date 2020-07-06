import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CommonChild {

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
        //    s1 0  1  2  ...  5000
        // s2 0    
        //    1
        //    2
        // 如s1 = 1, s2 = 0，s1剩最后一个char，s2只剩empty string
        // int[][] saved = new int[5001][5001];
        int length1 = s1.length();
        int length2 = s2.length();
        int[][] saved = new int[length1 + 1][length2 + 1];

        // 从小到大，小的可以重复利用
        for (int i = 0; i <= length1; i++) {
            for (int j = 0; j <= length2; j++) {
                // 其中一方empty string，必然无common child
                if (i == 0 || j == 0) {
                    saved[i][j] = 0;
                } else if (s1.charAt(length1 - i) == s2.charAt(length2 - j)) {
                    // 假如i = 1, 要的是s1的最后一个char，index = length - 1
                    saved[i][j] = 1 + saved[i - 1][j - 1];
                } else { // s1.charAt(i) != s2.charAt(j)
                    saved[i][j] = Math.max(saved[i - 1][j], saved[i][j - 1]);
                }
            }
        }

        return saved[length1][length2];

        /*
        if (s1.isEmpty() || s2.isEmpty()) { // 其中一方empty string，必然无common child
            return 0;
        } else if (s1.length() == 1 && s2.length() == 1) { 
            // 这个branch无必要，完全可以被另两个branch取代
            // 俩人都只有一个char，比一比这个char即可
            if (s1.equals(s2)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (s1.charAt(0) == s2.charAt(0)) {
                return 1 + commonChild(s1.substring(1), s2.substring(1));
            } else {
                // bothNo的情况，没可能比一方no的情况还大
                //int bothNo = commonChild(s1.substring(1), s2.substring(1));
                int s1No = commonChild(s1.substring(1), s2);
                int s2No = commonChild(s1, s2.substring(1));

                // 看谁最大
                //return Math.max(Math.max(bothNo, s1No), s2No);
                return Math.max(s1No, s2No);
            }
        }
        */
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
