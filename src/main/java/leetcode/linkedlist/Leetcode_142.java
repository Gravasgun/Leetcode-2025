package leetcode.linkedlist;

public class Leetcode_142 {
    /**
     * Leetcode 142. 环形链表 II
     *
     * 思路：
     * - 使用快慢指针（Floyd 判圈算法）。
     *
     * 第一步：判断链表是否有环
     * - 定义 fast 指针每次走 2 步，slow 指针每次走 1 步。
     * - 如果 fast 或 fast.next == null，说明无环。
     * - 如果有环，fast 和 slow 一定会在环中相遇。
     *
     * 第二步：找到环的入口
     * - 假设：
     *   链表头到环入口的距离 = a
     *   环入口到相遇点的距离 = b
     *   环的长度 = c
     *
     * - 相遇时：
     *   slow 走的路程 = a + b
     *   fast 走的路程 = 2(a + b)
     *   fast 比 slow 多走了 (a + b)
     *
     * - 关键点：
     *   fast 之所以能追上 slow，说明 fast 至少绕环了一圈。
     *   因此 (a + b) 一定等于环长 c（或其倍数，这里考虑第一次相遇，就是正好一圈）。
     *   所以：a + b = c
     *   推导得：a = c - b
     *
     * - 解释公式：
     *   a = 从头节点到环入口的距离
     *   c - b = 从相遇点到环入口的距离
     *   等价于 "从头节点到环入口的距离=从相遇点到环入口的距离"
     *   此时，用两个变量，一个从头节点开始走，一个从相遇点开始走，那么再相遇的那个点，就是起点
     *   所以：从头节点和相遇点同时出发，每次走一步，必然会在环入口相遇。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
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
                ListNode index2 = head; // 从头结点出发

                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1; // 返回环入口
            }
        }
        return null; // 没有环
    }
}