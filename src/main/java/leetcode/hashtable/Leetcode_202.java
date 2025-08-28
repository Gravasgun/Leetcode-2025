package leetcode.hashtable;

import java.util.HashSet;
import java.util.Set;

public class Leetcode_202 {
    /**
     * 判断一个数是否是快乐数 (Happy Number)
     *
     * 快乐数定义：
     * - 对于一个正整数，不断将它每一位的数字平方后相加，得到一个新的数字；
     * - 重复这个过程，直到结果为 1，则这个数是快乐数；
     * - 如果无限循环且不能得到 1，则不是快乐数。
     *
     * 例如：
     * n = 19
     * 1² + 9² = 82
     * 8² + 2² = 68
     * 6² + 8² = 100
     * 1² + 0² + 0² = 1 → 所以 19 是快乐数。
     */
    public boolean isHappy(int n) {
        // 用来检测是否出现循环
        // 因为如果一个数字重复出现，就说明进入了死循环，不可能再变成 1
        Set<Integer> set = new HashSet<>();

        // 循环条件：
        // 1. n != 1：还没变成快乐数
        // 2. !set.contains(n)：当前数字没有出现过（避免死循环）
        while (n != 1 && !set.contains(n)) {
            set.add(n);  // 把当前数字加入集合，标记为出现过
            n = getNextNumber(n); // 计算下一个数
        }

        // 循环退出后，如果 n == 1，说明是快乐数；否则是陷入循环
        return n == 1;
    }

    /**
     * 工具方法：计算下一个数
     * 规则：将 n 的每一位拆开，平方后求和
     * 例如：n = 82
     * 8² + 2² = 64 + 4 = 68
     */
    private int getNextNumber(int n) {
        int sum = 0;
        while (n > 0) {
            int temp = n % 10;   // 取出最后一位
            temp = temp * temp;  // 该位数字平方
            sum += temp;         // 累加到结果
            n = n / 10;          // 去掉最后一位
        }
        return sum; // 返回新生成的数字
    }
}
