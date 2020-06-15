import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Doubly {

    static class DoublyLinkedListNode {
        public int data;
        public DoublyLinkedListNode next;
        public DoublyLinkedListNode prev;

        public DoublyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
            this.prev = null;
        }
    }

    static class DoublyLinkedList {
        public DoublyLinkedListNode head;
        public DoublyLinkedListNode tail;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
            }

            this.tail = node;
        }
    }

    public static void printDoublyLinkedList(DoublyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }

    // Complete the sortedInsert function below.

    /*
     * For your reference:
     *
     * DoublyLinkedListNode {
     *     int data;
     *     DoublyLinkedListNode next;
     *     DoublyLinkedListNode prev;
     * }
     *
     */
    static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {
        // create the new node
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);

        // change the new node's previous and next
        // change the previous node's next
        // change the next node's previous
        if (head == null) {
            newNode.prev = null;
            newNode.next = null;
            return newNode;
        } else if (head.data >= data) {
            // no previous node
            newNode.prev = null;
            newNode.next = head;
            head.prev = newNode;
            return newNode;
        } else {
            DoublyLinkedListNode cur = head;
            // 要么中途有node的data >= data，截停
            // 要么被最后一个node截停，无论最后一个node的data是大是小
            while (cur.data < data && cur.next != null) {
                cur = cur.next;
            }

            // 没有next node
            if (cur.data < data) {
                cur.next = newNode;

                newNode.next = null;
                newNode.prev = cur;
            } else {
                DoublyLinkedListNode prev = cur.prev;
                // change the next node's previous
                cur.prev = newNode;
                // change the new node's previous and next
                newNode.next = cur;
                newNode.prev = prev;
                // change the previous node's next
                prev.next = newNode;
            }
            return head;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            DoublyLinkedList llist = new DoublyLinkedList();

            int llistCount = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < llistCount; i++) {
                int llistItem = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                llist.insertNode(llistItem);
            }

            int data = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            DoublyLinkedListNode llist1 = sortedInsert(llist.head, data);


          //  printDoublyLinkedList(llist1, " ", bufferedWriter);
         //   bufferedWriter.newLine();
        }

      //  bufferedWriter.close();

        scanner.close();
    }
}

