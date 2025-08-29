package leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        char[] chars1 = ransomNote.toCharArray();
        char[] chars2 = magazine.toCharArray();
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (char c : chars1) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for (char c : chars2) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        for (Character character : map1.keySet()) {
            if (map2.containsKey(character) && map2.get(character).intValue() >= map1.get(character).intValue()) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
