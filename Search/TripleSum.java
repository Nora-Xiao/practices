import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.regex.*;

public class TripleSum {

    // Complete the triplets function below.
    static long triplets(int[] a, int[] b, int[] c) {
        // sort并去掉duplicates
        List<Integer> temp = new ArrayList<>();
        for (int e : a) {
            temp.add(e);
        }
        TreeSet<Integer> setA = new TreeSet<>(temp);

        temp = new ArrayList<>();
        for (int e : b) {
            temp.add(e);
        }
        TreeSet<Integer> setB = new TreeSet<>(temp);

        temp = new ArrayList<>();
        for (int e : c) {
            temp.add(e);
        }
        TreeSet<Integer> setC = new TreeSet<>(temp);

        // 分开弄，a和b弄一轮，b和c再弄一轮

        // q and count for p (q >= p)
        HashMap<Integer, Integer> qAndP = new HashMap<>();
        // array b从小到大，3 6
        // array a从小到大，3 5 7
        // 比3都小的elements 必然比6小
        // 上一轮走过的 + 这一轮走的
        int overForA = 0;
        for (int q : setB) {
            // setA.size()每轮都会变，会在中途变，不行
            int setASize = setA.size();
            // setA.size()
            for (int j = 0; j < setASize; j++) {
                if (q < setA.first()) {
                    break;
                }
                setA.pollFirst();
                overForA++;
            }
            qAndP.put(q, overForA);
        }
/*
        for (int i = 0; i < b.length; i++) {
            for (int j = overForA; j < a.length; j++) {
                if (b[i] < a[j]) {
                    break;
                }
                overForA++;
            }
            qAndP.put(b[i], overForA);
        }

 */

        long count = 0;
        // q >= r
        // array b从小到大，3 6
        // array c从小到大，4 6 7
        // 比3都小的elements 必然比6小
        int overForC = 0;
        for (int q : setB) {
            int setCSize = setC.size();
            for (int j = 0; j < setCSize; j++) {
                if (q < setC.first()) {
                    break;
                }
                setC.pollFirst();
                overForC++;
            }
            // 若int * int，则 = int, 会overflow
            long countForA = qAndP.get(q);
            count += countForA * overForC;
        }
        /*
        for (int i = 0; i < b.length; i++) {
            for (int j = overForC; j < c.length; j++) {
                if (b[i] < c[j]) {
                    break;
                }
                overForC++;
            }
            int countForA = qAndP.get(b[i]);
            count += countForA * overForC;
        }
         */

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FileDescriptor.out));

        String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
