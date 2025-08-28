package leetcode.hashtable;

import java.util.HashSet;
import java.util.Set;

public class Leetcode_349 {
    /**
     * 题目：给定两个整数数组 nums1 和 nums2，
     * 返回它们的交集（intersection）。
     * - 交集中的每个元素必须唯一；
     * - 返回的结果可以是任意顺序。
     *
     * 解题思路：
     * 1. 用 HashSet 存储 nums1 的所有元素（保证唯一性，并支持 O(1) 查找）。
     * 2. 遍历 nums2，如果某个元素在 nums1 的 set 中出现，就加入结果集 resultSet。
     * 3. 最终把 resultSet 转换为 int[] 返回。
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // 用来存储 nums1 的所有元素，去重 + 快速查找
        Set<Integer> set = new HashSet<>();

        // 用来存储交集结果，保证元素唯一
        Set<Integer> resultSet = new HashSet<>();

        // 遍历 nums1，把所有元素放到 set 中
        for (int num : nums1) {
            set.add(num);
            // HashSet 自动去重，相同元素只会存一份
        }

        // 遍历 nums2，判断是否存在于 nums1 的 set 中
        for (int num : nums2) {
            if (set.contains(num)) {
                // 如果 num 存在于 nums1 的集合中，说明是交集元素
                resultSet.add(num);
                // 用 HashSet 存储结果，保证交集结果唯一
            }
        }

        // 创建结果数组，大小等于交集元素个数
        int[] result = new int[resultSet.size()];

        // 用 index 来依次写入 result 数组
        int index = 0;
        for (Integer integer : resultSet) {
            result[index++] = integer;
        }

        // 返回最终的交集数组
        return result;
    }
}
