package leetcode.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class Leetcode_142 {
    /**
     * Leetcode 142. 环形链表 II
     *
     * 方法一：Floyd 判圈算法（快慢指针法）
     *
     * 核心思想：
     * 1. 第一步：先用快慢指针判断链表是否有环。
     *    - fast 每次走 2 步，slow 每次走 1 步。
     *    - 如果 fast 或 fast.next == null，说明无环。
     *    - 如果 fast == slow，说明有环，且相遇点一定在环内。
     *
     * 2. 第二步：找到环的入口节点。
     *    - 假设：
     *      链表头到环入口的距离 = a
     *      环入口到相遇点的距离 = b
     *      环的长度 = c
     *
     *    - slow 走的路程 = a + b
     *    - fast 走的路程 = 2(a + b)
     *    - fast 比 slow 多走了 (a + b)，也就是刚好多走了一圈（c）
     *      所以 a + b = c，得到 a = c - b
     *
     *    - 结论：
     *      从头节点走 a 步会到达环入口；
     *      从相遇点走 c - b 步也会到达环入口。
     *      所以让一个指针从头节点出发，一个指针从相遇点出发，
     *      两者每次走一步，必然会在环入口相遇。
     *
     * 时间复杂度：O(n) —— 最多遍历两次链表
     * 空间复杂度：O(1) —— 只用两个指针
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        // 1. 判断是否有环
        while (fast != null && fast.next != null) {
            fast = fast.next.next; // fast 每次走两步
            slow = slow.next;      // slow 每次走一步

            if (fast == slow) { // 相遇，说明有环
                // 2. 找环入口
                ListNode index1 = fast; // 从相遇点出发
                ListNode index2 = head; // 从头节点出发

                // 两个指针同步走，必然在环入口相遇
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1; // 返回环的入口节点
            }
        }
        return null; // 没有环
    }


    /**
     * 方法二：HashSet 辅助法
     *
     * 思路：
     * - 遍历链表，把每个节点存入 HashSet。
     * - 在遍历过程中，如果发现某个节点已经存在于 Set 中，
     *   说明链表有环，且当前节点就是环的入口。
     *
     * 时间复杂度：O(n) —— 遍历整个链表一次
     * 空间复杂度：O(n) —— 需要一个 Set 存储节点
     *
     * ⚠️ 注意：这里存储的是节点对象（引用），
     * 不是节点的值 (val)，因为判定环是看节点是否重复，而不是值是否重复。
     */
    public ListNode detectCycle2(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode temp = head;

        while (temp != null) {
            if (set.contains(temp)) {
                return temp; // 找到第一个重复的节点，就是环入口
            } else {
                set.add(temp);   // 存入当前节点
                temp = temp.next; // 指针后移
            }
        }
        return null; // 没有环
    }
}
