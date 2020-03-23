import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CountTriplets {
    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        long counts = 0;

        // number of each of the elements on the right side of this element
        Map<Long, Long> right = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            long thisElement = arr.get(i);

            if (right.containsKey(thisElement)) {
                long count = right.get(thisElement);
                right.put(thisElement, count + 1);
            } else {
                right.put(thisElement, (long) 1);
            }

            /*
            if (thisElement % r == 0 || thisElement == 1) {
                if (right.containsKey(thisElement)) {
                    long count = right.get(thisElement);
                    right.put(thisElement, count + 1);
                } else {
                    right.put(thisElement, (long) 1);
                }
            }
            */

        }

        // number of each of the elements on the left side of this element
        Map<Long, Long> left = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            long thisElement = arr.get(i);

            // right里这个element要-1
            long rightCount = right.get(thisElement);
            right.put(thisElement, rightCount - 1);

            // 若r != 1，1不能做中间element，因此进不来
            if (thisElement % r == 0) {
                long lastElement = thisElement / r;
                long nextElement = thisElement * r;
                if (left.containsKey(lastElement) && right.containsKey(nextElement)) {
                    long count0 = left.get(lastElement);
                    long count2 = right.get(nextElement);
                    counts += count0 * 1 * count2;
                }
            }

            // 为下一个element做准备，left里这个element要+1
            if (left.containsKey(thisElement)) {
                long count = left.get(thisElement);
                left.put(thisElement, count + 1);
            } else {
                left.put(thisElement, (long) 1);
            }


            /*
            if (thisElement % r == 0 || thisElement == 1) {
                long lastElement = thisElement / r;
                long nextElement = thisElement * r;

                // right里这个element要-1
                long rightCount = right.get(thisElement);
                right.put(thisElement, rightCount - 1);

                if (left.containsKey(lastElement) && right.containsKey(nextElement)) {
                    long count0 = left.get(lastElement);
                    long count2 = right.get(nextElement);
                    counts += count0 * 1 * count2;

                }

                // 为下一个element做准备，left里这个element要+1
                if (left.containsKey(thisElement)) {
                    long count = left.get(thisElement);
                    left.put(thisElement, count + 1);
                } else {
                    left.put(thisElement, (long) 1);
                }
            }
            */


            /*
            long thisElement = arr.get(i);

            int log = (int) (Math.log(thisElement) / Math.log(r));
            long reconstruct = (long) Math.pow(r, log);

            if (thisElement == reconstruct) {
                long lastElement = thisElement / r;
                long nextElement = thisElement * r;

                // right里这个element要-1
                long rightCount = right.get(thisElement);
                right.put(thisElement, rightCount - 1);

                if (left.containsKey(lastElement) && right.containsKey(nextElement)) {
                    long count0 = left.get(lastElement);
                    long count2 = right.get(nextElement);
                    counts += count0 * 1 * count2;

                }

                // 为下一个element做准备，left里这个element要+1
                if (left.containsKey(thisElement)) {
                    long count = left.get(thisElement);
                    left.put(thisElement, count + 1);
                } else {
                    left.put(thisElement, (long) 1);
                }
            }

             */

        }

        return counts;
        /*
        long counts = 0;
        Map<Integer, Integer> a = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            long thisElement = arr.get(i);
            // 2^3 = 8
            // log(2^3) = log8
            // 3log2 = log8
            // 3 = log8 / log2
            double log = Math.log((double) thisElement) / Math.log((double) r);
            int intLog = (int) log;
            // integer
            if (log == intLog) {
                if (a.containsKey(intLog)) {
                    int count = a.get(intLog);
                    a.put(intLog, count + 1);
                } else {
                    a.put(intLog, 1);
                }
            }
        }

        for (int key : a.keySet()) {
            // key打头，key, key + 1, key + 2
            if (a.containsKey(key) && a.containsKey(key + 1) && a.containsKey(key + 2)) {
                int count0 = a.get(key);
                int count1 = a.get(key + 1);
                int count2 = a.get(key + 2);
                counts += count0 * count1 * count2;
            }
        }

        return counts;

         */
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        String[] arrItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Long> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr.add(arrItem);
        }

        long ans = countTriplets(arr, r);
        System.out.println(ans);

        //bufferedWriter.write(String.valueOf(ans));
        //bufferedWriter.newLine();

        bufferedReader.close();
        //bufferedWriter.close();
    }
}
