package leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_242 {
    /**
     * 判断两个字符串是否是字母异位词（anagram）
     * 字母异位词的定义：两个字符串包含的字符相同，且每个字符出现的次数也相同，
     * 但顺序可以不同。例如 "anagram" 和 "nagaram"。
     */
    public boolean isAnagram(String s, String t) {
        // 将字符串转换成字符数组，便于逐个遍历
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();

        // 使用 HashMap<Character, Integer> 统计每个字符出现的次数
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        // 遍历第一个字符串的字符数组
        // map1.getOrDefault(c, 0) 表示如果 map1 中不存在这个 key，就返回默认值 0
        // 然后加 1，表示字符出现次数+1
        for (char c : chars1) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        // 遍历第二个字符串，做相同的字符计数
        for (char c : chars2) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }

        /**
         * 这里直接使用 map1.equals(map2)
         *
         * 在 Java 中，Map 的 equals 方法定义是：
         * - 当且仅当两个 Map 拥有完全相同的 key-value 映射时，返回 true；
         * - 判断时会逐个比较 key 是否相同，以及对应的 value 是否相等；
         * - value 的比较使用 equals 方法，而不是 ==；
         * - key 的顺序不重要（因为 Map 是无序的），只要键值对一致，就认为两个 Map 相等。
         *
         * 举例：
         *   {a=2, b=1} 与 {b=1, a=2}  → true
         *   {a=2, b=1} 与 {a=2, b=2}  → false
         *   {a=1} 与 {a=1, b=0}       → false
         */
        return map1.equals(map2);
    }
}
