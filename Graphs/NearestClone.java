package com.siemens.slaco.itcompact.controller;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class NearestClone {

    // Complete the findShortest function below.

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */
    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        // build a tree
        HashMap<Integer, List<Integer>> t = new HashMap<>();
        // graphFrom和To，length一样长，是一对
        for (int i = 0; i < graphFrom.length; i++) {
            int from = graphFrom[i];
            int to = graphTo[i];
            if (!t.containsKey(from)) {
                t.put(from, new ArrayList<Integer>());
            }
            if (!t.containsKey(to)) {
                t.put(to, new ArrayList<Integer>());
            }
            List<Integer> l1 = t.get(from);
            l1.add(to);
            List<Integer> l2 = t.get(to);
            l2.add(from);
        }

        // find the same colour, i + 1 = 那个node
        List<Integer> findId = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            // val是要找的colour，ids是colour ids
            if (val == ids[i]) {
                findId.add(i + 1);
            }
        }

        if (findId.size() <= 1) {
            return -1;
        }

        // 1 2 3
        // 只需要loop 2
        int times = findId.size() - 2;

        // 可能是数字，或者-1
        int minLength = helper(t, findId, graphNodes);
        for (int i = 0; i < times; i++) {
            int min = helper(t, findId, graphNodes);
            // 假如minLength是数字，min < minLength && min != -1
            // 假如minLength是-1，replace它
            boolean number = min < minLength && min != -1;
            if (minLength == -1 || number) {
                minLength = min;
            }
        }

        return minLength;
    }

    private static int helper(HashMap<Integer, List<Integer>> t, List<Integer> findId, int graphNodes) {
        // 从list最后开始，每一轮拿一个node做起点，找同一个colour的其它node
        // 用bfs
        boolean[] visited = new boolean[graphNodes];
        int[] distance = new int[graphNodes];
        Queue<Integer> q = new LinkedList<>();
        int minLength = -1;

        int lastI = findId.size() - 1;
        int last = findId.get(lastI);
        findId.remove(lastI);

        q.add(last);
        visited[last - 1] = true;
        distance[last - 1] = 0;
        while (!q.isEmpty()) {
            int node1 = q.remove();
            if (findId.contains(node1)) {
                minLength = distance[node1 - 1];
                break;
            }
            List<Integer> l = t.get(node1);
            for (int i = 0; i < l.size(); i++) {
                int node2 = l.get(i);
                if (visited[node2 - 1] == false) {
                    visited[node2 - 1] = true;
                    distance[node2 - 1] = distance[node1 - 1] + 1;
                    q.add(node2);
                }
            }
        }

        return minLength;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FileDescriptor.out));

        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

