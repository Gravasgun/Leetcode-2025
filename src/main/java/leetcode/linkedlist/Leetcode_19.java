package leetcode.linkedlist;

public class Leetcode_19 {

    /**
     * 方法一：两次遍历法
     * 思路：
     * 1. 第一次遍历得到链表总长度 length。
     * 2. 第二次遍历到 (length - n) 位置的前一个节点，然后删除它的下一个节点。
     * 时间复杂度 O(n)，空间复杂度 O(1)。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0); // 虚拟头节点，简化边界情况
        dummyHead.next = head;
        ListNode current = dummyHead;

        // 1. 统计链表总长度
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }

        // 2. 计算需要移动的步数：走到待删除节点的前驱
        int moveTimes = length - n;
        for (int i = 0; i < moveTimes; i++) {
            current = current.next;
        }

        // 3. 删除目标节点
        current.next = current.next.next;

        return dummyHead.next;
    }


    /**
     * 方法二：一遍扫描（快慢指针）
     * 思路：
     * - 用 fast 和 slow 两个指针，同时从虚拟头节点出发。
     * - 先让 fast 指针比 slow 快 n+1 步（注意是 n+1）。
     *   为什么是 n+1：
     *   - 因为 slow 要停在「待删除节点的前驱」位置。
     *   - 如果 fast 只快 n 步，最后 slow 会停在「待删除节点」上，无法直接操作它的前驱。
     *   - 多走 1 步，保证最终 slow 正好停在「要删除节点的前一个节点」。
     * - 然后 fast 和 slow 同时移动，直到 fast 到达末尾。
     * - 此时 slow 的下一个节点就是要删除的目标节点。
     *
     * 时间复杂度 O(n)，空间复杂度 O(1)。
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode slow = dummyHead;
        ListNode fast = dummyHead;

        // 1. fast 先走 n+1 步，制造一个间隔
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        // 2. fast 和 slow 一起走，直到 fast 到达末尾
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 3. 此时 slow 停在待删除节点的前驱，删除 slow.next
        slow.next = slow.next.next;

        return dummyHead.next;
    }
}
