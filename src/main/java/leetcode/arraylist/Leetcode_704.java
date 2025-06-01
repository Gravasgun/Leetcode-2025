package leetcode.arraylist;

import java.util.Scanner;

/**
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 */
public class Leetcode_704 {
    /**
     * 二分查找（左闭右闭区间写法）
     * 在一个升序排列的整数数组中查找目标值 target，如果找到则返回其索引，否则返回 -1。
     * <p>
     * 时间复杂度：O(log n)
     * 空间复杂度：O(1)
     *
     * @param nums   升序排列的整数数组
     * @param target 目标值
     * @return 目标值的索引，如果不存在返回 -1
     */
    public static int search(int[] nums, int target) {
        // 左指针初始化为数组起始索引
        int left = 0;
        // 右指针初始化为数组最后一个索引
        int right = nums.length - 1;
        // 为什么是 <= 而不是 <？
        // 因为我们采用的是“左闭右闭”区间 [left, right]
        // 所以当 left == right 时，[left, right] 仍然是一个有效区间
        // 如果改成 <，比如 [5] target=5 时，left=0，right=0，循环不进入，直接返回 -1，结果就错了
        while (left <= right) {
            // 计算中间索引
            // 使用 left + (right - left) / 2 避免整数溢出
            int middle = left + (right - left) / 2;
            // 比较中间值与目标值
            if (nums[middle] > target) {
                // 目标值在中间值左侧，修改右边界
                right = middle - 1;
            } else if (nums[middle] < target) {
                // 目标值在中间值右侧，修改左边界
                left = middle + 1;
            } else {
                // 找到目标值，返回索引
                return middle;
            }
        }
        // 未找到目标值，返回 -1
        return -1;
    }

    //ACM模式
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入数组长度：");
        int length = input.nextInt();
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            System.out.println("请输入数组内的元素：");
            nums[i] = input.nextInt();
        }
        System.out.println("请输入目标值：");
        int target = input.nextInt();
        int result = search(nums, target);
        System.out.println("结果是：" + result);
    }

}
