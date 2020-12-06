package com.blankj.coding_interviews._38;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution {
/*    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }*/

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        cloneNodes(head);
        connectSiblingNodes(head);
        return reconnectNextNode(head);
    }

    private Node reconnectNextNode(Node head) {
        Node cur = head;
        Node cloneHead = null;
        Node cloneNode = null;
        if (cur != null) {
            cloneHead = cloneNode = cur.next;
            cur.next = cloneHead.next;
            cur = cur.next;
        }
        while (cur != null) {
            cloneNode.next = cur.next;
            cloneNode = cloneNode.next;
            cur.next = cloneNode.next;
            cur = cur.next;
        }
        return cloneHead;
    }

    private void connectSiblingNodes(Node head) {
        Node cur = head;
        while (cur != null) {
            Node cloneNode = cur.next;
            Node sibling = cur.random;
            if (sibling != null) {
                cloneNode.random = sibling.next;
            }
            cur = cloneNode.next;
        }
    }

    private void cloneNodes(Node head) {
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            Node cloneNode = new Node(cur.val);
            cur.next = cloneNode;
            cloneNode.next = next;
            cur = next;
        }
    }

    public static void main(String[] args) {
        //
    }
}