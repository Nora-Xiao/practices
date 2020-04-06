import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MakingAnagrams {
    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        int[] freqA = new int[26];
        int[] freqB = new int[26];

        for (char aChar : aChars) {
            int index = ((int) aChar) - 97;
            freqA[index] += 1;
        }
        for (char bChar : bChars) {
            int index = ((int) bChar) - 97;
            freqB[index] += 1;
        }

        int sub = 0;
        for (int i = 0; i < 26; i++) {
            int fromA = freqA[i];
            int fromB = freqB[i];
            sub += Math.abs(fromA - fromB);
        }
        return sub;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
