package leetcode.linkedlist;

public class Leetcode_203 {
    /**
     * Leetcode 203. 移除链表元素
     * 给定一个链表的头节点 head 和一个整数 val，
     * 删除链表中所有等于 val 的节点，并返回新的头节点。
     *
     * 思路：
     * - 使用虚拟头节点（dummyHead）来简化删除逻辑，避免头节点需要单独判断的情况。
     * - 从 dummyHead 开始遍历链表：
     *    - 如果当前节点的下一个节点值等于 val，则跳过这个节点（删除）。
     *    - 否则移动指针，继续遍历。
     *
     * ⚠️ 易错点：
     * 1. while 循环中 **不能写成无论是否删除都 current = current.next**，
     *    否则会漏删连续的目标节点。
     *    例如链表 [1, 6, 6, 3], val=6：
     *      - 删除第一个 6 后，current 直接跳到第二个 6，
     *        结果第二个 6 没被检查到，最终残留。
     * 2. 正确做法：删除时 current 不前进，只在 else 中前进。
     */
    public ListNode removeElements(ListNode head, int val) {
        // 定义虚拟头节点，指向真正的 head
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        // 遍历指针，从 dummyHead 开始
        ListNode current = dummyHead;

        while (current.next != null) {
            if (current.next.val == val) {
                // 删除目标节点：跳过它
                current.next = current.next.next;
                // 注意：这里 current 不前进！
            } else {
                // 只有当不删除时才前进
                current = current.next;
            }
        }

        return dummyHead.next;
    }
}
