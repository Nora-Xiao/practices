import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Frequency {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        // data structure
        HashMap<Integer, Integer> struct = new HashMap<>();
        // frequency and its count
        HashMap<Integer, Integer> freqCount = new HashMap<>();
        // for print
        List<Integer> print = new ArrayList<>();

        // loop over the queries
        for (int i = 0; i < queries.size(); i++) {
            int type = queries.get(i).get(0);
            int val = queries.get(i).get(1);

            // add
            if (type == 1) {
                Integer freq = struct.get(val);
                int newFreq;
                if (freq != null) {
                    newFreq = freq + 1;

                    // former frequency's count - 1
                    Integer count = freqCount.get(freq);
                    freqCount.put(freq, count - 1);
                } else {
                    newFreq = 1;
                }

                struct.put(val, newFreq);

                // current frequency's count + 1
                if (freqCount.containsKey(newFreq)) {
                    Integer count = freqCount.get(newFreq);
                    freqCount.put(newFreq, count + 1);
                } else {
                    freqCount.put(newFreq, 1);
                }
            } else if (type == 2) { // delete
                Integer freq = struct.get(val);
                if (freq != null) {
                    if (freq == 1) {
                        struct.remove(val);
                    } else {
                        int newFreq = freq - 1;
                        struct.put(val, newFreq);

                        // current frequency's count + 1
                        if (freqCount.containsKey(newFreq)) {
                            Integer count = freqCount.get(newFreq);
                            freqCount.put(newFreq, count + 1);
                        } else {
                            freqCount.put(newFreq, 1);
                        }
                    }

                    // former frequency's count - 1
                    Integer count = freqCount.get(freq);
                    freqCount.put(freq, count - 1);
                }
            } else { // print
                // struct.containsValue(val)最差O(n)
                Integer count = freqCount.get(val);
                if (count != null && count > 0) {
                    print.add(1);
                } else {
                    print.add(0);
                }
            }
        }

        return print;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // new FileWriter(System.getenv("PATH"))
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FileDescriptor.out));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
