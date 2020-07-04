import java.io.*;
import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.regex.*;

public class Inversions {

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {
        return mergeSort(arr, 0, arr.length);
    }

    static long mergeSort(int[] a, int start, int end) {
        if (end - start <= 1) {
            return 0;
        } else {
            long count = 0;
            int mid = (start + end) / 2;
            count += mergeSort(a, start, mid);
            count += mergeSort(a, mid, end);
            count += merge(a, start, mid, end);
            return count;
        }
    }

    static long merge(int[] a, int start, int mid, int end){
        long count = 0;

        int[] left_arr = Arrays.copyOfRange(a, start, mid);
        int[] right_arr = Arrays.copyOfRange(a, mid, end);

        int left_i = 0, right_i = 0;
        for (int i = start; i < end; i++) {
            if (left_i == left_arr.length) {
                a[i] = right_arr[right_i++];
            } else if (right_i == right_arr.length) { // right没了
                a[i] = left_arr[left_i++];
            } else if(right_arr[right_i] < left_arr[left_i]){ // right放
                a[i] = right_arr[right_i++];
                count += left_arr.length - left_i;
            } else { // left放
                a[i] = left_arr[left_i++];
            }
        }

        /*
        int i = start;
        // left和right还有
        while (left_i < left_arr.length && right_i < right_arr.length) {
            if (left_arr[left_i] > right_arr[right_i]) { // right放
                a[i] = right_arr[right_i];
                count += left_arr.length - left_i;
                right_i++;
            } else { // left放
                a[i] = left_arr[left_i];
                left_i++;
            }
            i++;
        }

        // left没了, 只剩right。不用invert, array直接可以
        // right没了，得放left。但不用invert, 之前放right的时候，已经把left搓到了正确的位置
        if (right_i == right_arr.length) {
            for (int j = i; j < end; j++) {
                a[j] = left_arr[left_i];
                left_i++;
            }
        }
        */

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FileDescriptor.out));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

