package com.blankj.medium._61;

import com.blankj.structure.ListNode;

public class RotateRight {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        int num = 1;
        while (p.next != null) {
            p = p.next;
            num++;
        }
        p.next = head;
        k %= num;
        for (int i = 0; i < num - k; i++) {
            p = p.next;
        }
        head = p.next;
        p.next = null;
        return head;
    }

    public static void main(String[] args) {
        new RotateRight().rotateRight(ListNode.createTestData("[1,2,3,4,5]"), 2);
    }
}
