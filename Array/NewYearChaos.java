import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// arrays
public class NewYearChaos {
    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int totalBribes = 0;
        boolean chaotic = false;

        // 从倒数第2个element开始，如果比next element大，往后挪
        for (int i = q.length - 2; i >= 0; i--) {
            // i, i + 1
            int times = 2;
            // i
            if (i == q.length - 2) {
                times = 1;
            }
            for (int j = 0; j < times; j++) {
                int thisElement = q[i + j];
                int nextElement = q[i + j + 1];
                if (thisElement > nextElement) {
                    // System.out.println("thisElement" + " " + thisElement + " nextElement " + nextElement);
                    totalBribes++;
                    q[i + j] = nextElement;
                    q[i + j + 1] = thisElement;
                    // System.out.println("thisElement" + " " + q[j] + " nextElement " + q[j + 1]);
                }
            }
            if (i + 3 < q.length) {
                int thirdNext = q[i + 3];
                if (thirdNext < q[i + 2]) {
                    System.out.println("Too chaotic");
                    chaotic = true;
                    break;
                }
            }
        }

        if (!chaotic) {
            System.out.println(totalBribes);
        }

        // 假如currentPosition < originalPosition, 这个element必然是从后面bribe上来的
        // 至于这个element bribe了1次，还是2次，抑或too chaotic，就得看了

        /*
        int totalBribes = 0;
        boolean chaotic = false;
        for (int i = 0; i < q.length; i++) {
            int originalPosition = q[i]; // e.g. 2
            int currentPosition = i + 1; // e.g. 1
            int bribe = 0;
            if (currentPosition < originalPosition) {
                bribe = originalPosition - currentPosition;
            }
            System.out.println("originalPosition" + " " + originalPosition + " " +
                    "currentPosition" + currentPosition + " " + "bribe" + " " + bribe);

            if (bribe > 2) {
                System.out.println("Too chaotic");
                chaotic = true;
                break;
            } else {
                totalBribes += bribe;
            }
        }
        if (!chaotic) {
            System.out.println(totalBribes);
        }

         */

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
