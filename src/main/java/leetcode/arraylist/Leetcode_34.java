package leetcode.arraylist;

public class Leetcode_34 {
    /**
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * <p>
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRangeMethodOne(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                int i = middle;
                while (i >= 0) {
                    if (nums[i] == target) {
                        i--;
                    } else {
                        break;
                    }
                }
                int j = middle;
                while (j < nums.length) {
                    if (nums[j] == target) {
                        j++;
                    } else {
                        break;
                    }
                }
                return new int[]{i + 1, j - 1};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 使用双二分法分别查找左边界和右边界
     * 重点：
     *  1.要理解怎么找的左边界和右边界
     *  2.找到左右边界之后，要判断是否存在目标值：
     *      情况一：leftborder=-2 以及rightborder=-2 说明数组中不存在目标值
     *      情况二：必须满足 rightborder - leftBorder > 1 因为 [leftBorder,rightBorder] 区间内起码有一个值，且这个值为target
     *      情况三：如果rightborder - leftBorder <= 1，说明[leftBorder,rightBorder] 区间内没有值，也就是没有target
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRangeMethodTwo(int[] nums, int target) {
        // 获取 target 左边界（即第一个小于 target 的位置）
        int leftBorder = searchLeftBorder(nums, target);
        // 获取 target 右边界（即第一个大于 target 的位置）
        int rightBorder = searchRightBorder(nums, target);
        // 如果任一边界没有更新，说明 target 根本不存在于数组中
        if (leftBorder == -2 || rightBorder == -2) {
            return new int[]{-1, -1};
        }
        //判断 rightBorder - leftBorder > 1	表示 target 至少存在一次也就是说数组区间内有target
        if (rightBorder - leftBorder > 1) {
            return new int[]{leftBorder + 1, rightBorder - 1};
        }
        // 否则 target 不存在
        return new int[]{-1, -1};
    }

    /**
     * 查找右边界（第一个大于 target 的下标）
     * 例如 nums = [1,2,3,3,3,4], target = 3
     * 返回的是下标 5（即 nums[5] = 4 > 3）
     */
    private int searchRightBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int rightBorder = -2; // 初始值为 -2，标记未找到

        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] <= target) {
                // 当前值小于等于 target，说明目标右边界还在右边
                left = middle + 1;
                rightBorder = left; // 更新 rightBorder 为“第一个大于 target 的位置”
            } else {
                // 当前值大于 target，继续在左半边找
                right = middle - 1;
            }
        }

        return rightBorder;
    }

    /**
     * 查找左边界（最后一个小于 target 的下标）
     * 例如 nums = [1,2,3,3,3,4], target = 3
     * 返回的是下标 1（即 nums[1] = 2 < 3）
     */
    private int searchLeftBorder(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int leftBorder = -2; // 初始值为 -2，标记未找到

        while (left <= right) {
            int middle = (left + right) / 2;
            //当nums[middle]大于target，更新(缩小)右边界
            //当nums[middle]等于target，不停止，继续更新(缩小)右边界，所以才能找到左边界
            if (nums[middle] >= target) {
                right = middle - 1;
                leftBorder = right; // 更新 leftBorder 为“最后一个小于 target 的位置”
            } else {
                // 当前值小于 target，继续在右半边找
                left = middle + 1;
            }
        }
        return leftBorder;
    }
}
