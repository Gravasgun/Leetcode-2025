package leetcode.linkedlist;

public class Leetcode_707 {
    int size;            // 链表中实际存储的数据节点数（不包括虚拟头节点）
    ListNode dummyHead;  // 虚拟头节点（哨兵节点）

    public Leetcode_707() {
        this.size = 0;
        this.dummyHead = new ListNode(0); // 初始化虚拟头节点，值无意义
    }

    /**
     * 获取链表中下标为 index 的节点的值
     * 合法区间：0 <= index < size
     */
    public int get(int index) {
        if (index >= size || index < 0) {
            return -1; // 越界，返回 -1
        }
        ListNode temp = dummyHead;
        // 从 dummyHead 出发，走 index+1 步，才能到达目标节点
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.val;
    }

    /**
     * 在链表头部插入节点
     * 等价于在下标 0 位置插入
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * 在链表尾部插入节点
     * 等价于在下标 size 位置插入
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * 在指定下标 index 插入节点
     * 合法区间：0 <= index <= size
     * - index == size → 插入到链表尾部
     * - index < 0 → 视为在头部插入
     * - index > size → 不合法，直接返回
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return; // 超过当前链表长度，插入无效
        }
        if (index < 0) {
            index = 0; // index 小于 0，按 0 处理（插入到头部）
        }
        size++;
        ListNode temp = dummyHead;
        // 找到 index 位置的前驱节点（从 dummyHead 出发走 index 步）
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        ListNode newNode = new ListNode(val);
        ListNode next = temp.next;
        temp.next = newNode;
        newNode.next = next;
    }

    /**
     * 删除下标为 index 的节点
     * 合法区间：0 <= index < size
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return; // 越界，删除无效
        }
        size--;
        ListNode temp = dummyHead;
        // 找到 index 节点的前驱节点
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next; // 删除目标节点
    }
}
