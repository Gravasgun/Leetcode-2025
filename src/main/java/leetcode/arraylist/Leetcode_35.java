package leetcode.arraylist;

public class Leetcode_35 {
    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 请必须使用时间复杂度为 O(log n) 的算法。
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        /*
         * 为什么返回 left 是正确的插入位置？
         *
         * 1. 循环结束条件是 left > right，此时搜索区间为空。
         * 2. 最后一次循环中，要么是 left = middle + 1，要么是 right = middle - 1，
         *    所以循环结束后有 left == right + 1 恒成立。
         * 3. 因为我们查找的是第一个“≥ target”的位置，
         *    所以此时的 left 恰好指向 target 应该插入的位置（即保持数组有序）。
         *
         * 例如：
         * nums = [1, 3, 5, 6], target = 2
         * 最终 left = 1，nums[1] = 3，说明 2 应插在下标 1。
         *
         * ✅ 因此返回 left 即可。如果你返回 right + 1 也没错，本质是一样的。
         */
        return left;
    }

}
