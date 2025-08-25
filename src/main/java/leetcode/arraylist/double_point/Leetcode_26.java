package leetcode.arraylist.double_point;

public class Leetcode_26 {
    /**
     * 题目：删除排序数组中的重复项
     * 要求：必须在原地删除重复元素，使每个元素只出现一次，并返回新数组的长度。
     * 数组必须保持有序。
     *
     * 解题思路：
     * 使用双指针法（快慢指针）。
     * - slow 指针：指向新数组的最后一个有效元素。
     * - fast 指针：从头到尾扫描数组。
     * 如果 fast 指针遇到一个与 nums[slow] 不同的元素，
     * 就说明找到了一个新的不重复元素，把它放到 slow+1 位置上，
     * 然后 slow 前进一格。
     */
    public int removeDuplicates(int[] nums) {
        // 慢指针 slow，初始指向第 0 个元素（第一个元素一定是有效的）
        int slow = 0;

        // 快指针 fast 从第 1 个元素开始遍历
        for (int fast = 1; fast < nums.length; fast++) {
            // 如果 fast 指向的值与 slow 不同，说明遇到了新元素
            if (nums[fast] != nums[slow]) {
                // 先将 slow 前进一格，然后把 fast 的值写到新位置上
                nums[++slow] = nums[fast];
                // 等价于：
                // slow++;
                // nums[slow] = nums[fast];
            }
            // 如果 nums[fast] == nums[slow]，说明是重复元素，直接跳过
        }

        // 循环结束时，slow 指向最后一个不重复元素的下标
        // 长度应该是下标 + 1，所以返回 ++slow
        return ++slow;
    }
}
