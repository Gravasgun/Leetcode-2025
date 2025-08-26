package leetcode.arraylist.slide_window;

import java.util.HashSet;
import java.util.Set;

public class Leetcode_3 {
    /**
     * Leetcode 3. 无重复字符的最长子串
     * 给定一个字符串 s，找到其中不含重复字符的最长子串的长度。
     *
     * 思路：滑动窗口（双指针 + 哈希表/集合）
     * - 用 left 和 right 维护一个窗口 [left, right]，窗口内始终保证没有重复字符
     * - right 指针不断向右扩展，把新字符尝试加入窗口
     * - 如果新字符在窗口中已存在（出现冲突），说明窗口不合法，
     *   就通过移动 left 指针收缩窗口，直到窗口合法（即去掉了重复字符）
     * - 在窗口合法时，用当前窗口长度更新答案
     *
     * 时间复杂度：O(n)，每个字符最多进窗口一次、出窗口一次
     * 空间复杂度：O(k)，k 为字符集大小（ASCII 最多 128）
     */
    public int lengthOfLongestSubstring(String s) {
        int left = 0;                // 窗口左指针
        int maxLength = 0;           // 记录最长子串的长度
        Set<Character> set = new HashSet<>(); // 维护当前窗口的字符集合（保证唯一性）
        char[] chars = s.toCharArray();

        // 枚举右指针
        for (int right = 0; right < chars.length; right++) {
            char c = chars[right];

            // 如果新字符 c 已经在窗口里，窗口就不合法
            // 不断移动 left 指针，移出左边字符，直到窗口合法（set 中不再含有 c）
            while (set.contains(c)) {
                set.remove(chars[left]);
                left++;
            }

            // 此时窗口合法，可以安全地加入新字符 c
            set.add(c);

            // 更新最长子串长度
            // 窗口长度 = right - left + 1
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
