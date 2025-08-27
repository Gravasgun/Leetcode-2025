package leetcode.linkedlist;

import java.util.Stack;

public class Leetcode_206 {
    /**
     * 方法一：栈解法
     * 时间复杂度 O(n)，空间复杂度 O(n)
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null; // 空链表，直接返回
        }

        // 1. 遍历链表，把所有节点压入栈
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        // 2. 构造虚拟头节点 dummyHead（方便处理新链表头）
        ListNode dummyHead = new ListNode(0);

        // 3. 弹出第一个节点（原链表的最后一个节点），作为新的头节点
        dummyHead.next = stack.pop();
        ListNode current = dummyHead.next;

        // 4. 继续出栈，把节点依次接在 current 后面
        while (!stack.isEmpty()) {
            temp = stack.pop();   // 出栈一个节点
            current.next = temp;  // 连接到当前链表尾部
            current = current.next; // 更新尾指针
        }

        // 5. ❗ 最后一定要断开尾节点的 next，避免形成环
        current.next = null;

        return dummyHead.next;
    }

    /**
     * 方法二：双指针迭代（推荐）
     * 时间复杂度 O(n)，空间复杂度 O(1)
     *
     * 思路：
     * - 用 pre 指向已经反转好的部分
     * - 用 current 指向当前正在处理的节点
     * - 用 temp 保存 current 的下一个节点（防止链表断开）
     *
     * 步骤：
     * 1. 保存 current.next 到 temp
     * 2. 反转指针：current.next = pre
     * 3. pre 前进：pre = current
     * 4. current 前进：current = temp
     * 循环直到 current 为 null
     *
     * 最终 pre 就是新的头节点
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return null; // 空链表，直接返回
        }

        ListNode pre = null;         // 反转链表的前一节点（初始为空）
        ListNode current = head;     // 当前处理的节点

        while (current != null) {
            ListNode temp = current.next; // 1. 保存下一个节点
            current.next = pre;           // 2. 指针反转
            pre = current;                // 3. pre 前进
            current = temp;               // 4. current 前进
        }

        return pre; // pre 指向反转后的头节点
    }
}
