package leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_454 {
    /**
     * 题目：给定四个整数数组 nums1, nums2, nums3, nums4，
     * 统计有多少个元组 (i,j,k,l) 满足：
     * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     *
     * 解题思路（哈希表优化）：
     * 1. 将问题拆分为两部分：
     *    (nums1[i] + nums2[j]) + (nums3[k] + nums4[l]) == 0
     * 2. 用一个 HashMap 存储「nums1[i] + nums2[j] 的和 → 出现次数」；
     * 3. 遍历 (nums3[k] + nums4[l])，查找它的相反数是否在 HashMap 中；
     *    - 如果在，就说明有匹配的前半部分；
     *    - 次数累加到结果中。
     *
     * ⚠️ 注意：
     * 必须存“次数”，而不是只存“是否存在”。
     * 因为 nums1[i] + nums2[j] 可能会出现多次，每一次都可以和 nums3[k] + nums4[l] 配对。
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int result = 0;

        // map 用来存储「两个数之和」以及它出现的次数
        Map<Integer, Integer> map = new HashMap<>();

        // 第一步：枚举 nums1 和 nums2 的所有组合
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                // 把 sum 加入 map，并记录出现次数
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        // 第二步：枚举 nums3 和 nums4 的所有组合
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int sum = nums3[i] + nums4[j];
                int target = -sum; // 我们需要找 -(nums3[i] + nums4[j])

                // 如果 map 里有 target，就把它的出现次数加到结果里
                if (map.containsKey(target)) {
                    int count = map.get(target);
                    result += count;
                }
            }
        }

        return result;
    }
}
