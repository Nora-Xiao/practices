import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FraudulentActivityNotifications {
    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        int total = 0;
        int[] past = new int[201]; // index from 0 to 200 (inclusive)
        for (int i = 0; i < d; i++) {
            past[expenditure[i]]++;
        }

        for (int i = d; i < expenditure.length; i++) {
            int thisTransaction = expenditure[i];
            // d = 5, 要找第3个number
            // d = 6, 要找3和4, mid = 4
            int mid = d / 2 + 1;

            // count和index对应同一个number
            int count = 0;
            int index = -1;
            while (count < mid) {
                index++;
                count += past[index];
            }

            double median;
            if (d % 2 == 0) { // even
                // index与比其小的一个数
                // 比其小的数，要么与index相等，要么比index小1
                int countBefore = count - past[index];
                if (mid - countBefore == 1) { // 比index小的数，必然是index - 1
                    median = (index + (index - 1)) / 2.0;
                } else { // 比index小的数，必然是index
                    median = (index + index) / 2.0;
                }
            } else {
                median = index;
            }
            System.out.println(median);

            if (thisTransaction >= 2 * median) {
                total++;
                // System.out.println(thisTransaction);
            }

            past[expenditure[i - d]]--;
            past[thisTransaction]++;
        }

        System.out.println(total);
        return total;

        /*
        int total = 0;
        List<Integer> past = new ArrayList<>();
        for (int i = 0; i < d; i++) {
            past.add(expenditure[i]);
        }
        Collections.sort(past);
        for (int i = d; i < expenditure.length; i++) {
            double median;
            int mid = d / 2;
            if (d % 2 == 0) { // even
                // 0, 1, 2, 3
                // 如d = 4，d / 2 = 2，需要index = 1和2的
                median = (past.get(mid - 1) + past.get(mid)) / 2.0;
            } else {
                // 0, 1, 2
                // d = 3, d / 2 = 1
                median = past.get(mid);
            }

            int thisTransaction = expenditure[i];
            if (thisTransaction >= 2 * median) {
                total++;
                // System.out.println(thisTransaction);
            }

            // d = 3, 0 1 2 past
            // 这个是3
            past.remove((Integer) expenditure[i - d]);
            int index = 0;
            while (index != (d - 1) && thisTransaction > past.get(index)) {
                index++;
            }
            past.add(index, thisTransaction);
        }

        return total;

         */
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);
/*
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
*/
        scanner.close();
    }
}
