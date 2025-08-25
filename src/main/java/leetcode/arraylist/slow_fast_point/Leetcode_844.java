package leetcode.arraylist.slow_fast_point;

public class Leetcode_844 {
    /**
     * 题目：844. 比较含退格的字符串
     * 给定两个字符串 s 和 t，每个字符串可能包含字母和字符 '#'。
     * 字符 '#' 表示退格键。要求判断处理退格后，两个字符串是否相等。
     *
     * 示例：
     * 输入：s = "ab#c", t = "ad#c"
     * 输出：true
     * 解释：s 退格后变成 "ac"，t 退格后变成 "ac"，所以相等。
     */

    public boolean backspaceCompare(String s, String t) {
        // 将两个字符串都处理成“退格之后的有效结果字符串”
        String s1 = getTransferedString(s);
        String s2 = getTransferedString(t);
        // 比较处理后的结果是否相等
        return s1.equals(s2);
    }

    /**
     * 功能：把输入字符串 s 处理成退格后的结果
     * 思路：使用快慢指针模拟输入过程
     * - fast 指针：遍历原始字符串中的每个字符
     * - slow 指针：表示当前有效字符串的“长度”（下一个可写入的位置）
     */
    private String getTransferedString(String s) {
        // 将字符串转换为字符数组，方便操作
        char[] chars = s.toCharArray();

        int slow = 0; // 慢指针，指向有效字符的下一个存放位置
        // 遍历每个字符
        for (int fast = 0; fast < chars.length; fast++) {
            if (chars[fast] != '#') {
                // 如果不是退格符，就写入到 slow 位置
                chars[slow] = chars[fast];
                slow++; // 有效字符长度加 1
            } else {
                // 如果是退格符，并且 slow > 0（有字符可以删除）
                if (slow > 0) {
                    slow--; // 相当于删除上一个有效字符
                }
                // 如果 slow == 0，说明当前没有字符可删，直接跳过
            }
        }
        // 遍历完成后，前 slow 个字符就是退格后的有效结果
        // 使用 new String(chars, 0, slow) 来生成最终字符串
        return new String(chars, 0, slow);
    }
}
