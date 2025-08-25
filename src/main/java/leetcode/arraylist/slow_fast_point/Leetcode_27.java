package leetcode.arraylist.slow_fast_point;

public class Leetcode_27 {
    /**
     * 题目：移除数组中等于给定值的元素，并返回移除后数组的新长度。
     * 要求：不使用额外数组空间，必须在原数组上操作。
     *
     * 解题思路：
     * 使用双指针法（快慢指针）。
     * - fast 指针：遍历整个数组。
     * - slow 指针：记录“新数组”的下一个可填充位置。
     * 当 fast 指针遇到的值不等于 val 时，就把该值复制到 slow 指针的位置，然后 slow 前进一格。
     * 最终 slow 的值就是新数组的长度。
     */
    public int removeElement(int[] nums, int val) {
        // 慢指针 slow：表示当前“有效数组”的下一个位置
        int slow = 0;

        // 快指针 fast：遍历数组
        for (int fast = 0; fast < nums.length; fast++) {
            // 如果当前元素等于要删除的值 val
            if (nums[fast] == val) {
                // 跳过该元素，不做任何操作
                continue;
            } else {
                // 如果当前元素不等于 val，就把它复制到 slow 指针所在的位置
                nums[slow] = nums[fast];
                // slow 向前移动一格，准备写入下一个有效元素
                slow++;
            }
        }

        // 遍历完成后，slow 即为新数组的长度
        return slow;
    }
}
