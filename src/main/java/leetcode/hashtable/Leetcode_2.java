package leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_2 {
    /**
     * 解法一：暴力枚举
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * 思路：双重循环，检查所有 (i,j) 组合，是否满足 nums[i] + nums[j] == target
     */
    public int[] twoSum(int[] nums, int target) {
        // 遍历数组中的每一个元素
        for (int i = 0; i < nums.length; i++) {
            // 从 i+1 开始，避免重复和自己相加
            for (int j = i + 1; j < nums.length; j++) {
                // 判断两数之和是否等于 target
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j}; // 找到答案，直接返回下标
                }
            }
        }
        // 如果不存在符合条件的两个数，返回 [-1, -1]
        return new int[]{-1, -1};
    }

    /**
     * 解法二：哈希表优化
     * 时间复杂度：O(n)，空间复杂度：O(n)
     *
     * 关键点：
     * ⚠️ 千万不要写成「先存，再查」，否则会出现自己和自己配对的错误。
     * 举例：nums = [3,2,4], target = 6
     * 如果先存 3，再查 target-3=3，就会返回 {0,0}，错误！
     * 正确做法是「先查，再存」：
     *   - 先查：只在 map 中找之前出现过的数；
     *   - 再存：把当前数放进去，供后面的数来配对。
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(); // 存储「数值 → 下标」

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 需要的补数

            // ✅ 先查
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            // ✅ 再存
            // 这样保证不会出现「同一个元素和自己配对」的情况
            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }
}