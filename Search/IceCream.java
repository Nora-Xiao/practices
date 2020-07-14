import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class IceCream {

    // Complete the whatFlavors function below.
    static void whatFlavors(int[] cost, int money) {
        // key = cost
        // value = ids
        HashMap<Integer, List<Integer>> costAndId = new HashMap<>();
        for (int i = 0; i < cost.length; i++) {
            if (!costAndId.containsKey(cost[i])) {
                costAndId.put(cost[i], new ArrayList<Integer>());
            }
            List<Integer> l = costAndId.get(cost[i]);
            // array's index + 1 = id
            l.add(i + 1);
        }

        for (int c1 : costAndId.keySet()) {
            if ((2 * c1) == money) {
                List<Integer> l = costAndId.get(c1);
                if (l.size() >= 2) {
                    int id1 = l.get(0);
                    int id2 = l.get(1);
                    // 先放进list的，必然id小
                    System.out.println(id1 + " " + id2);
                    break;
                }
            } else { // 2个cost不同
                int c2 = money - c1;
                if (costAndId.containsKey(c2)) {
                    int id1 = costAndId.get(c1).get(0);
                    int id2 = costAndId.get(c2).get(0);
                    if (id1 < id2) {
                        System.out.println(id1 + " " + id2);
                    } else {
                        System.out.println(id2 + " " + id1);
                    }
                    break;
                }
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }
}
