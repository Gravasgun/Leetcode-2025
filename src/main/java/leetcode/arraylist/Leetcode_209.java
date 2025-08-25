package leetcode.arraylist;

public class Leetcode_209 {

    /**
     * 方法一：暴力解法（双重循环枚举子数组）
     * 时间复杂度：O(n^2)，会超时，但思路清晰。
     */
    public int minSubArrayLenMethodOne(int target, int[] nums) {
        // 记录最小子数组长度，初始设为最大整数
        int minLength = Integer.MAX_VALUE;

        // 枚举所有可能的子数组起点
        for (int i = 0; i < nums.length; i++) {
            int sum = 0; // 每次从 i 开始重新计算子数组的和

            // 从 i 开始，向右枚举子数组的终点 j
            for (int j = i; j < nums.length; j++) {
                sum += nums[j]; // 累加当前子数组的和

                // 一旦发现子数组和 >= target
                if (sum >= target) {
                    int length = j - i + 1; // 当前子数组的长度
                    if (length < minLength) {
                        minLength = length; // 更新最小长度
                    }
                    break; // 从 i 出发的更长子数组一定更长，无需继续
                }
            }
        }

        // 如果 minLength 没被更新过，返回 0，否则返回最小长度
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * 方法二：滑动窗口解法（双指针）
     * 时间复杂度：O(n)，空间复杂度：O(1)
     * 思路：
     * - 使用两个指针 left 和 right 维护一个窗口 [left, right]
     * - 不断扩大右边界，直到窗口内的和 >= target
     * - 然后尝试缩小左边界，找到最小长度
     */
    public static int minSubArrayLenMethodTwo(int target, int[] nums) {
        int left = 0;  // 左指针，表示窗口左边界
        int right = 0; // 右指针，表示窗口右边界
        int sum = nums[right]; // 当前窗口的和
        int minLength = Integer.MAX_VALUE; // 记录最小子数组长度

        // 右指针遍历数组
        while (right < nums.length) {
            if (sum >= target) {
                // 当前窗口和 >= target，尝试更新最小长度
                int length = right - left + 1;
                if (length < minLength) {
                    minLength = length;
                }

                // 缩小窗口（左指针右移）
                sum -= nums[left];
                left++;
            } else {
                // 当前窗口和 < target，扩展右边界
                right++;
                if (right >= nums.length) {
                    break; // 防止越界
                }
                sum += nums[right];
            }
        }

        // 如果没找到符合条件的子数组，返回 0
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        // 注意：printf 要加格式占位符 %d，否则不会打印数值
        System.out.printf("结果：%d\n", minSubArrayLenMethodTwo(7, nums));
        // 期望输出：2，因为子数组 [4,3] 长度为 2，和 >= 7
    }
}