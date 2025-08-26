package leetcode.arraylist.slide_window;

public class Leetcode_643 {
    /**
     * Leetcode 643. 子数组最大平均数 I
     * 给定一个整型数组 nums 和一个整数 k，
     * 找出长度为 k 的连续子数组的最大平均值，并返回这个最大平均值。
     *
     * 思路：典型的「固定长度滑动窗口」
     * - 用两个指针 left / right 维护一个大小为 k 的窗口
     * - 窗口右边界不断向右移动，累加 sum
     * - 当窗口长度达到 k 时，计算平均值，并尝试更新最大值
     * - 然后缩小窗口（移出最左边的元素，left++），保持窗口长度固定为 k
     */
    public double findMaxAverage(int[] nums, int k) {
        int left = 0;                       // 窗口左边界
        double result = Integer.MIN_VALUE;  // 记录最大平均值，初始为一个很小的数
        double sum = 0;                     // 当前窗口内元素的和

        // 特殊情况：如果数组长度小于 k，窗口无法形成长度为 k 的子数组
        // 那么直接返回整个数组的平均值
        if (nums.length < k) {
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            return sum / nums.length;
        }

        // 遍历数组，用 right 扩展窗口右边界
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right]; // 将 nums[right] 加入窗口
            int steps = right - left + 1; // 当前窗口长度
            // 当窗口长度达到 k 时，进行计算和更新
            if (steps >= k) {
                double average = sum / k;            // 计算当前窗口的平均值
                result = Math.max(result, average);  // 更新最大平均值

                // 移出窗口最左边的元素，缩小窗口
                sum -= nums[left];
                left++;
            }
        }
        // 返回最大平均值
        return result;
    }
}
