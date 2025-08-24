package leetcode.arraylist.binary_search;

public class Leetcode_34 {
    /**
     * 题目：给定一个升序排列的整数数组 nums 和一个目标值 target，
     * 找出目标值在数组中的起始位置和结束位置。如果数组中不存在目标值，返回 [-1, -1]。
     * 要求时间复杂度 O(log n)，因此必须使用二分查找。
     *
     * 思路：
     * 1. 通过二分查找分别找到 target 的“左边界”和“右边界”。
     * 2. 左边界：最后一个小于 target 的位置。
     *    右边界：第一个大于 target 的位置。
     * 3. 如果 rightBorder - leftBorder > 1，说明中间至少有一个 target 存在，
     *    那么结果区间就是 [leftBorder+1, rightBorder-1]。
     * 4. 否则，说明 target 不存在，返回 [-1, -1]。
     *
     * @param nums   升序排列的整数数组
     * @param target 目标值
     * @return 目标值的起始位置和结束位置
     */
    public int[] searchRange(int[] nums, int target) {
        int left = searchLeftBorder(nums, target);   // 找到左边界
        int right = searchRightBorder(nums, target); // 找到右边界

        // 如果任意一边为 -2，说明二分查找过程中边界值没有被更新，target 根本不在数组中
        if (left == -2 || right == -2) {
            return new int[]{-1, -1};
        }

        // 如果右边界和左边界之间至少有一个元素（说明 target 存在）
        if (right - left >= 1) {
            // 返回 target 的起始位置和结束位置
            // 注意：真实区间是 (left, right) —— 即去掉边界本身
            return new int[]{left + 1, right - 1};
        }

        // 否则 target 不存在
        return new int[]{-1, -1};
    }

    /**
     * 查找 target 的右边界（第一个大于 target 的位置）
     *
     * 例如 nums = [5,7,7,8,8,10], target = 8
     * 右边界应该返回下标 5（nums[5]=10，是第一个大于 8 的元素）
     *
     * 如果 target 大于所有元素，最终返回 nums.length
     *
     * @param nums   升序数组
     * @param target 目标值
     * @return 右边界下标（若未找到则返回 -2，表示无效）
     */
    private int searchRightBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int rightBorder = -2; // 初始值为 -2，用来区分“从未更新过”的情况

        while (left <= right) {
            int middle = (left + right) / 2; // 取中间位置
            if (nums[middle] <= target) {
                // 如果 nums[middle] <= target，说明右边界在右侧
                left = middle + 1;
                rightBorder = left; // 更新右边界为 left
            } else {
                // 如果 nums[middle] > target，右边界在左侧
                right = middle - 1;
            }
        }
        return rightBorder;
    }

    /**
     * 查找 target 的左边界（最后一个小于 target 的位置）
     *
     * 例如 nums = [5,7,7,8,8,10], target = 8
     * 左边界应该返回下标 2（nums[2]=7，是最后一个小于 8 的元素）
     *
     * 如果 target 小于所有元素，最终返回 -1
     *
     * @param nums   升序数组
     * @param target 目标值
     * @return 左边界下标（若未找到则返回 -2，表示无效）
     */
    private int searchLeftBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int leftBorder = -2; // 初始值为 -2，用来区分“从未更新过”的情况

        while (left <= right) {
            int middle = (left + right) / 2; // 取中间位置
            if (nums[middle] >= target) {
                // 如果 nums[middle] >= target，说明左边界在左侧
                right = middle - 1;
                leftBorder = right; // 更新左边界为 right
            } else {
                // 如果 nums[middle] < target，左边界在右侧
                left = middle + 1;
            }
        }
        return leftBorder;
    }
}
