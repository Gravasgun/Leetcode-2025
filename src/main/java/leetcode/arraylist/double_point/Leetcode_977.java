package leetcode.arraylist.double_point;

public class Leetcode_977 {
    /**
     * 题目：977. 有序数组的平方
     * 给定一个按非递减顺序排序的整数数组 nums，
     * 返回一个按非递减顺序排序的数组，其中每个元素等于 nums[i] 的平方。
     *
     * 示例：
     * 输入：nums = [-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     *
     * 解题思路：
     * - 原数组有序，但可能包含负数。
     * - 平方后，负数可能变大（例如 -4 的平方比 3 的平方大）。
     * - 因此需要找到“平方后最大的元素”，再按顺序放入结果数组。
     *
     * 方法：双指针 + 从大到小填充
     * - 左指针 left 指向数组开头
     * - 右指针 right 指向数组末尾
     * - 每次比较 |nums[left]| 和 |nums[right]|，平方较大的那个放到结果数组的末尾
     * - 填充位置由 index 从后往前递减
     */
    public int[] sortedSquares(int[] nums) {
        int left = 0;                     // 左指针，指向数组起始位置
        int right = nums.length - 1;      // 右指针，指向数组末尾位置
        int index = right;                // 从结果数组的最后一个位置开始填充
        int[] result = new int[nums.length]; // 存放最终结果的数组

        // 双指针向中间收缩，直到 left > right
        while (left <= right) {
            // 比较左右两端的平方值，选择较大者放到 result[index]
            if (nums[left] * nums[left] <= nums[right] * nums[right]) {
                result[index] = nums[right] * nums[right]; // 右边平方更大
                right--; // 右指针左移
            } else {
                result[index] = nums[left] * nums[left];  // 左边平方更大
                left++; // 左指针右移
            }
            index--; // 填充位置前移
        }

        return result; // 返回结果数组
    }
}
