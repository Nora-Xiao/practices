import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class QueueUsingTwoStacks {
    public static class MyQueue<T> {
        // elements: 1 2 3
        // stackNewestOnTop: 1 2 3
        // stackOldestOnTop: 3 2 1
        Stack<T> stackNewestOnTop = new Stack<T>();
        Stack<T> stackOldestOnTop = new Stack<T>();

        public void enqueue(T value) { // Push onto newest stack
            stackNewestOnTop.push(value);            
        }

        public T peek() {
            // 假如无if statement，直接while loop，第一把stackNewestOnTop: 1 2 3，peek n 次都没有问题，pop n 次也没有问题
            // 但是peek/pop完，回去enqueue就出现问题了。直接放进stackOldestOnTop不行。放进stackNewestOnTop的话，直接通过这里的while loop fill进stackOldestOnTop也不行。只能留在stackNewestOnTop里，等stackOldestOnTop里没element了，才能用while loop fill进来
            if (stackOldestOnTop.empty()) {
                // empty stackNewestOnTop, fill stackOldestOnTop as 3 2 1
                while (!stackNewestOnTop.empty()) {
                    T e = stackNewestOnTop.pop();
                    stackOldestOnTop.push(e);
                }
            }
            
            T front = stackOldestOnTop.peek();
            return front;
/*
            两遍loop太慢
            // empty stackNewestOnTop, fill stackOldestOnTop as 3 2 1
            while (!stackNewestOnTop.empty()) {
                T e = stackNewestOnTop.pop();
                stackOldestOnTop.push(e);
            }    
            T front = stackOldestOnTop.peek();

            // fill back stackNewestOnTop
            while (!stackOldestOnTop.empty()) {
                T e = stackOldestOnTop.pop();
                stackNewestOnTop.push(e);
            }

            return front;
        */              
        }

        public T dequeue() {
            if (stackOldestOnTop.empty()) {
                // empty stackNewestOnTop, fill stackOldestOnTop as 3 2 1
                while (!stackNewestOnTop.empty()) {
                    T e = stackNewestOnTop.pop();
                    stackOldestOnTop.push(e);
                }
            }
            T front = stackOldestOnTop.pop();
            return front;

/*
            // empty stackNewestOnTop, fill stackOldestOnTop as 3 2 1
            while (!stackNewestOnTop.empty()) {
                T e = stackNewestOnTop.pop();
                stackOldestOnTop.push(e);
            }
            T front = stackOldestOnTop.pop();

            // fill back stackNewestOnTop
            while (!stackOldestOnTop.empty()) {
                T e = stackOldestOnTop.pop();
                stackNewestOnTop.push(e);
            }
            
            return front;  
            */          
        }
    }

    
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();
        
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        
        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}