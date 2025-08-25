package leetcode.arraylist.slow_fast_point;

public class Leetcode_283 {
    /**
     * 题目：移动零 (Leetcode 283)
     * 要求：给定一个数组，将数组中所有的 0 移动到数组末尾，同时保持非零元素的相对顺序。
     * 必须在原数组上操作，不能使用额外数组空间。
     *
     * 解题思路：
     * 使用双指针（快慢指针）：
     * 1. fast 指针：遍历整个数组。
     * 2. slow 指针：记录下一个“非零元素”应该放置的位置。
     *    - 当 fast 指向的元素不为 0 时，就将该元素写到 slow 的位置，然后 slow++。
     *    - 遍历完成后，前 slow 个位置已经放好所有非零元素。
     * 3. 最后，把 slow 之后的位置全部填充为 0。
     */
    public void moveZeroes(int[] nums) {
        // 慢指针 slow：指向下一个非零元素应该放置的位置
        int slow = 0;

        // 快指针 fast：遍历整个数组
        for (int fast = 0; fast < nums.length; fast++) {
            // 如果当前元素不为 0，就放到 slow 指针的位置
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
            // 如果是 0，就跳过（不处理）
        }

        // 当遍历完成后，前 slow 个元素都是非零数
        // 从 slow 到数组末尾的位置全部置为 0
        for (; slow < nums.length; slow++) {
            nums[slow] = 0;
        }
    }
}
