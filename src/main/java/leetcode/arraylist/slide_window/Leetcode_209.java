package leetcode.arraylist.slide_window;

public class Leetcode_209 {

    /**
     * 方法一：暴力解法（双重循环枚举子数组）
     * 思路：从每个位置 i 出发，累加 nums[j]，直到 sum >= target
     *      然后记录子数组长度，并更新最小值。
     * 时间复杂度：O(n^2)，大数据时会超时。
     */
    public int minSubArrayLenMethodOne(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE; // 初始化为最大值，表示还没找到答案

        // 枚举所有可能的起点 i
        for (int i = 0; i < nums.length; i++) {
            int sum = 0; // 从 i 出发的子数组的和

            // 枚举子数组的终点 j
            for (int j = i; j < nums.length; j++) {
                sum += nums[j]; // 不断扩展子数组

                // 一旦和 >= target，就更新最小长度
                if (sum >= target) {
                    int length = j - i + 1;
                    minLength = Math.min(minLength, length);
                    break; // 因为继续扩展只会更长，不可能更优，直接跳出
                }
            }
        }

        // 如果没找到答案，就返回 0，否则返回结果
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * 方法二：滑动窗口解法（双指针）
     * 思路：
     * - 维护一个窗口 [left, right]，表示当前子数组。
     * - 每次把 nums[right] 加入窗口，更新 sum。
     * - 当 sum >= target 时，尝试移动 left（缩小窗口），直到 sum < target。
     * - 在这个过程中，记录最小的窗口长度。
     *
     * 时间复杂度：O(n)，每个元素最多被访问两次（一次进入窗口，一次被移出）。
     * 空间复杂度：O(1)。
     */
    public static int minSubArrayLenMethodTwo(int target, int[] nums) {
        int left = 0; // 窗口左边界
        int result = Integer.MAX_VALUE; // 记录最小子数组长度
        int sum = 0; // 当前窗口的和

        // 枚举右边界
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right]; // 把 nums[right] 加入窗口

            // 当窗口和 >= target，就尝试缩小窗口
            while (sum >= target) {
                result = Math.min(result, right - left + 1); // 更新最小长度
                sum -= nums[left]; // 移除左边的元素
                left++; // 左边界右移
            }
        }

        // 如果没找到，返回 0，否则返回结果
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        System.out.printf("结果：%d\n", minSubArrayLenMethodTwo(7, nums));
        // 期望输出：2，因为最短子数组是 [4,3]，长度为 2
    }
}
