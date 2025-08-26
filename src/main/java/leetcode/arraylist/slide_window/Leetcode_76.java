package leetcode.arraylist.slide_window;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_76 {
    /**
     * Leetcode 76 最小覆盖子串
     * 给定两个字符串 s 和 t，在 s 中找到包含 t 所有字符的最小子串。
     * 如果不存在这样的子串，返回 ""。
     */
    public String minWindow(String s, String t) {
        char[] chars1 = s.toCharArray(); // 将 s 转换为字符数组，方便索引访问
        char[] chars2 = t.toCharArray(); // 将 t 转换为字符数组

        // 如果 t 比 s 还长，必然不存在覆盖子串
        if (chars2.length > chars1.length) {
            return "";
        }

        // map: 记录 t 中每个字符的需求次数
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars2) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // resultMap: 记录当前窗口中每个字符出现的次数
        Map<Character, Integer> resultMap = new HashMap<>();

        int left = 0;                // 窗口左指针
        int valid = 0;               // 当前满足要求的字符种类数
        int start = 0;               // 记录最小覆盖子串的起始位置
        int minLength = Integer.MAX_VALUE; // 记录最小覆盖子串的长度

        // 遍历 s，用 right 扩展窗口右边界
        for (int right = 0; right < s.length(); right++) {
            char d = chars1[right];
            resultMap.put(d, resultMap.getOrDefault(d, 0) + 1);

            // ⚠️ Integer 和 int 的坑：
            // 千万不要写 map.get(d) == resultMap.get(d)
            // 因为 Integer 在 [-128,127] 范围内会缓存，== 可能“看起来对”，但超过 127 就会错（比较的是对象引用）。
            // 正确写法：用 .intValue() 或 .equals() 来比较数值。
            if (map.containsKey(d) && map.get(d).intValue() == resultMap.get(d).intValue()) {
                valid++;
            }

            // 当窗口包含了 t 的所有字符时（即窗口合法），开始收缩窗口
            while (valid == map.size()) {
                int length = right - left + 1;
                // ⚠️ 关于构造结果字符串的坑：
                // 【错误做法】：每次都 result = new String(chars1, left, length);
                // 这样会频繁 new 字符串对象 → O(n^2) → 大数据时超时（TLE）。
                // 【正确做法】：只记录 start 和 minLength，最后只 new 一次字符串，效率高。
                if (length < minLength) {
                    minLength = length;
                    start = left; // 记录最优窗口的起始位置
                }

                // 缩小窗口：移出左边界的字符
                char c = chars1[left];
                if (map.containsKey(c) && resultMap.get(c).intValue() == map.get(c).intValue()) {
                    valid--; // 移出前正好满足需求 → 移出后不满足
                }
                resultMap.put(c, resultMap.get(c) - 1); // 窗口内对应字符次数 -1
                left++; // 左指针右移
            }
        }

        // 如果 minLength 没更新过，说明没有找到覆盖子串
        return minLength == Integer.MAX_VALUE ? "" : new String(chars1, start, minLength);
    }
}
