package src.sorting;

import src.tools.*;

public class LinkedList {
    public Node head;

    private static class Node {
        Comparable data;
        Node next;

        Node(Comparable d) {
            data = d;
            next = null;
        }
    }
    void insert(Comparable data) {
        /*
         * 1 & 2: Allocate the Node & Put in the data
         */
        Node new_node = new Node(data);
        /* 3. Make next of new Node as head */
        new_node.next = head;
        /* 4. Move the head to point to new Node */
        head = new_node;
    }

    static void printList(Node node) {
        Node temp = node;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    static int size(Node first) {
        if (first == null)
            return 0;
        if (first.next == null)
            return 1;
        int N = 1;
        Node temp = first;
        while (temp.next != null) {
            N++;
            temp = temp.next;
        }
        return N;
    }

    static Node getMiddle(Node first) {
        if (first == null)
            return first;
        Node slow = first, fast = first;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static Node sort(Node first) {
        if (first.next == null)
            return first;

        Node middle = getMiddle(first);
        Node middlePlusOne = middle.next;
        middle.next = null;
        Node left = sort(first);
        Node right = sort(middlePlusOne);
        return merge(left, right);
    }

    static Node merge(Node a, Node b) {
        if (b == null)
            return a;
        if (a == null)
            return b;
        Node result = new Node(null); // set the head of the result node
        for (Node current = result;; current = current.next) // will be broken when either a or b is null
         { 
            if (a == null) { // if a is null copy rest of b and return
                while (b != null) {
                    current.next = b;
                    b = b.next;
                    current = current.next;
                }
                break;
            } else if (b == null) { // if b is null copy rest of a and return
                while (a != null) {
                    current.next = a;
                    a = a.next;
                    current = current.next;
                }
                break;
            } else { // copy smallest of a,b into result
                if (a.data.compareTo(b.data) < 0) {
                    current.next = a;
                    a = a.next;
                } else {
                    current.next = b;
                    b = b.next;
                }
            }
        }
        return result.next;
    }

    public static boolean isSorted(Node head) {
        if (size(head) == 1)
            return true;
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data.compareTo(temp.data) < 0)
                return false;
            temp = temp.next;
        }
        return true;
    }

    public static void main(String[] args) {

        int N = 1000000;
        LinkedList test1 = new LinkedList();
        for (int i = 0; i < N; i++) {
            test1.insert(StdRandom.uniform(0, 20));
        }
        Node result = sort(test1.head);
        // printList(result);
        System.out.println(isSorted(result));
    }

}
