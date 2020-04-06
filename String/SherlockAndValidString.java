import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlockAndValidString {
    // Complete the isValid function below.
    static String isValid(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char thisChar = chars[i];
            if (!counts.containsKey(thisChar)) {
                counts.put(thisChar, 1);
            } else {
                int preCount = counts.get(thisChar);
                counts.put(thisChar, preCount + 1);
            }
        }
        System.out.println(counts);

        int count1 = 0;
        int count2 = 0;
        int letters1 = 0;
        int letters2 = 0;
        for (int count : counts.values()) {
            if (count1 == 0) { // 只有第1轮进的来
                count1 = count;
            } else if (count != count1 && count2 == 0) { // 出现新count且count2 == 0时
                count2 = count;
            }

            // 下面是count == count1 || count == count2
            if (count != count1 && count != count2) { // 出现第3个count时
                return "NO";
            } else if (count == count1) {
                letters1++;
            } else { // count == number2
                letters2++;
            }
        }

        // 下面，只有2个count

        // 只有一个count, 所有letters都是相同的count
        if (count2 == 0) {
            return "YES";
        }

        // 把这唯一一个letter还只出现了一次 扔出去就行，另一个是多少count 有多少letters无所谓
        if ((count1 == 1 && letters1 == 1) || (count2 == 1 && letters2 == 1)) {
            return "YES";
        }

        if (Math.abs(count1 - count2) != 1) { // 不知道谁大谁小
            return "NO";
        } else { // 这俩count差1
            if (count1 > count2) { // letters1得 = 1
                // 大count的letters = 1，把大count的那个letter减1个count就行
                if (letters1 == 1) {
                    return "YES";
                } else {
                    return "NO";
                }
            } else { // count1 < count2
                if (letters2 == 1) {
                    return "YES";
                } else {
                    return "NO";
                }
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);
        System.out.println(result);
        /*
        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();
        */
        scanner.close();
    }
}
