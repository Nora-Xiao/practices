import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MaxArraySum {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        /* 从后往前，从小到大
          （假如只算包括的情况，1 2 3 4 5，1和3、1和4、1和5都得搞一遍，不现实）
           无论包括不包括，最后一个，max = 自己
           倒数第2个，max = 自己 or 最后一个
           1 2 3
           1，2，3，
           1 3
           倒数第3个，max = 只要自己
                          自己 + 最后一个（自己 - 2）
                          倒数第2个（自己 - 1）
                          最后1个（自己 - 2）不用这个，倒数第2个已经包括这种情况了
           1 2 3 4
           1，2，3，4
           1 3，1 4，2 4
           倒数第4个，max = 只要自己
                          自己 +（自己 - 2）
                          自己 - 1
                          自己 - 2，去掉                   
        */

        int[] saved = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            // 最后一个
            if (i == arr.length - 1) {
                saved[i] = arr[i];
            } else if (i == arr.length - 2) { // 倒数第二个
                saved[i] = Math.max(arr[i], saved[i + 1]);
            } else {
                // 只要自己，与自己+(自己 - 2)battle
                int temp = Math.max(arr[i], arr[i] + saved[i + 2]);
                // 再与(自己 - 1)battle
                saved[i] = Math.max(temp, saved[i + 1]);
            }
        }

        return saved[0];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
