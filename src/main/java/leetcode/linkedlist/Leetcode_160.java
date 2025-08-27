package leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class Leetcode_160 {
    /**
     * Leetcode 160. 相交链表
     *
     * 思路（HashSet 解法）：
     * - 遍历链表 A，把所有节点存入 HashSet。
     * - 遍历链表 B，检查当前节点是否出现在 HashSet 里。
     *   - 如果出现，说明这个节点就是两个链表的第一个相交点。
     *   - 如果 B 遍历结束也没找到，说明两条链表不相交。
     *
     * ⚠️ 注意：
     * - 判断“相交”是指节点对象相同（即地址相同），不是值相同。
     *   所以这里存放的是 ListNode 节点，而不是节点的 val。
     *
     * 时间复杂度：O(m + n)，其中 m 和 n 分别是两个链表的长度。
     * 空间复杂度：O(m)，存储链表 A 的所有节点。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();

        // 1. 遍历链表 A，把所有节点存入 HashSet
        ListNode current = headA;
        while (current != null) {
            set.add(current);      // 存储节点对象（不是节点值）
            current = current.next; // 指针后移
        }

        // 2. 遍历链表 B，找第一个出现在 HashSet 的节点
        ListNode temp = headB;
        while (temp != null) {
            if (set.contains(temp)) {
                return temp; // 找到第一个相交点，立即返回
            }
            temp = temp.next;
        }

        // 3. 如果 B 遍历完都没找到，说明没有相交
        return null;
    }
}
