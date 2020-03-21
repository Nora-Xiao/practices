import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Array2D {
    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        // 搞到16个左上角的index
        // 每个最小为-9，7个element最小的sum为-63
        int maxSum = -63;
        for (int row = 0; row <= 3; row++) {
            for (int col = 0; col <= 3; col++) {
                int thisSum = getSum(row, col, arr);
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    static int getSum(int firstRow, int firstCol, int[][] arr) {
        int sum = 0;
        for (int i = firstCol; i <= firstCol + 2; i++) {
            sum += arr[firstRow][i];
        }
        sum += arr[firstRow + 1][firstCol + 1];
        for (int i = firstCol; i <= firstCol + 2; i++) {
            sum += arr[firstRow + 2][i];
        }
        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
