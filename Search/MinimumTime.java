import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MinimumTime {

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
        long max = machines[0];
        for (int i = 1; i < machines.length; i++) {
            if (machines[i] > max) {
                max = machines[i];
            }
        }

        return bs(machines, goal, 1, max * goal);
        /*
        double oneDayMachines = 0;
        for (int i = 0; i < machines.length; i++) {
            oneDayMachines += 1.0 / machines[i];
        }
        return (long) Math.ceil(goal / oneDayMachines);
         */
    }

    // 此minTime和maxTime都为possible time
    // 举例minTime = 1, maxTime = 3，1 2 3为possible times
    static long bs(long[] machines, long goal, long minTime, long maxTime) {
        if (minTime == maxTime) {
            return minTime;
        } else {
            long midTime = (minTime + maxTime) / 2;
            // is midTime possible?
            // finished items in midTime
            long finished = 0;
            for (int i = 0; i < machines.length; i++) {
                finished += midTime / machines[i];
            }

            // midTime不work, 时间太少了
            if (finished < goal) {
                return bs(machines, goal, midTime + 1, maxTime);
            } else { // midTime时间够，有可能多了，有可能正好
                return bs(machines, goal, minTime, midTime);
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FileDescriptor.out));

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
