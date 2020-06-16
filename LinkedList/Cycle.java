/*
Detect a cycle in a linked list. Note that the head pointer may be 'null' if the list is empty.

A Node is defined as: 
    class Node {
        int data;
        Node next;
    }
*/

boolean hasCycle(Node head) {
    // 2 nodes, one moves at 1 time speed, one moves at 2 times speed
    Node fast = head;
    Node slow = head;
    
    // 只有无cycle的，才会有null
    // 假如要null, 也是fast null。slow还没移动到那里
    // fast一次移动2格/2个next，所以要检查fast和fast.next
    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
        
        // 必须slow.equals(fast)，而非fast.equals(slow)
        // 因为fast此时有可能是null。当fast null时，slow不null
        if (slow.equals(fast)) {
            return true;
        }
    }
    return false;
}