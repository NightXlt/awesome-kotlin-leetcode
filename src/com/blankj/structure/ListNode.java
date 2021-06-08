package com.blankj.structure;


/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/05/18
 *     desc  :
 * </pre>
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    /**
     * 创建测试数据
     *
     * @param data [XX,XX,XX]
     * @return {@link ListNode}
     */
    public static ListNode createTestData(String data) {
        if (data == null || data.equals("[]")) return null;
        data = data.substring(1, data.length() - 1);
        String[] split = data.split(",");
        int len = split.length;
        ListNode[] listNode = new ListNode[len + 1];
        listNode[0] = new ListNode(Integer.valueOf(split[0]));
        for (int i = 1; i < len; i++) {
            listNode[i] = new ListNode(Integer.valueOf(split[i]));
            listNode[i - 1].next = listNode[i];
        }
        return listNode[0];
    }

    public static ListNode testCase0() {
        return ListNode.createTestData("[0]");
    }

    public static ListNode testCase1() {
        return ListNode.createTestData("[4,5,1,9]");
    }

    public static ListNode testCase2() {
        return ListNode.createTestData("[5,5,5,5]");
    }

    public static ListNode testCase3() {
        return ListNode.createTestData("[1,2,3,4,5]");
    }

    public static ListNode palindromeLinkedList() {
        return ListNode.createTestData("[1,2,2,1]");
    }

    public static void print(ListNode listNode) {
        if (listNode == null) {
            System.out.println("null");
            return;
        }
        StringBuilder str = new StringBuilder("[" + String.valueOf(listNode.val));
        ListNode p = listNode.next;
        while (p != null) {
            str.append(",").append(String.valueOf(p.val));
            p = p.next;
        }
        System.out.println(str.append("]"));
    }
}
