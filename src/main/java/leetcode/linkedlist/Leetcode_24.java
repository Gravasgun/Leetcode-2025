package leetcode.linkedlist;

public class Leetcode_24 {
    /**
     * Leetcode 24. 两两交换链表中的节点
     *
     * 思路：
     * - 使用虚拟头节点 dummyHead 简化操作，避免处理头节点时的特殊情况。
     * - 每次循环处理两个节点，交换它们的位置。
     * - 用指针 current 指向待交换的前驱节点。
     *
     * while 条件解释：
     * - 条件是 (current.next != null && current.next.next != null)
     * - 意思是：必须至少有两个节点（node1 = current.next，node2 = current.next.next）
     *   才能组成一对，进行交换。
     *
     * 和链表长度的关系：
     * - n = 0 → 空链表，循环不执行
     * - n = 1 → 只有一个节点，无法成对，循环不执行
     * - n = 2 → 恰好一对，交换一次
     * - n = 3 → 前两个交换，最后一个节点保持不变
     * - n = 4 → 前两对都能交换，全部交换完成
     *
     * 规律：
     * - 链表长度为偶数 → 所有节点都能参与交换
     * - 链表长度为奇数 → 最后一个节点没有配对，保持原样
     *
     * 时间复杂度：O(n)，空间复杂度：O(1)
     */
    public ListNode swapPairs(ListNode head) {
        // 定义虚拟头节点，指向真正的头节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // current 指向每次要交换的两个节点的前驱
        ListNode current = dummyHead;

        // 只要 current 后面至少有两个节点，才能进行交换
        while (current.next != null && current.next.next != null) {
            // 保存两个待交换的节点
            ListNode temp = current.next;             // node1
            ListNode temp2 = current.next.next.next;  // node2 的下一个节点

            // 交换操作
            current.next = current.next.next;  // current -> node2
            temp.next.next = temp;             // node2 -> node1
            temp.next = temp2;                 // node1 -> node2 的下一个节点

            // current 前进两步，指向下一组的前驱
            current = current.next.next;
        }

        // 返回新的头节点
        return dummyHead.next;
    }
}
