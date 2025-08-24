package leetcode.arraylist.binary_search;

public class leetcode_367 {
    /**
     * 判断一个正整数 num 是否是完全平方数。
     *
     * 思路：
     * - 可以用二分查找在区间 [0, num] 内搜索平方根。
     * - 每次取中点 middle，计算 middle^2 与 num 比较：
     *   1. 如果 middle^2 == num，说明找到了，返回 true；
     *   2. 如果 middle^2 < num，说明平方太小，答案应该在右边 → left = middle + 1；
     *   3. 如果 middle^2 > num，说明平方太大，答案应该在左边 → right = middle - 1；
     * - 如果最终没找到，返回 false。
     *
     * 时间复杂度：O(log n)，每次搜索区间缩小一半。
     * 空间复杂度：O(1)，只用到常数个变量。
     *
     * 注意点：
     * - 用 (long)middle * middle 来避免 int 溢出。
     *   比如当 num=2,147,483,647 时，middle≈46341，
     *   middle*middle=2,147,488,281 > int 最大值，会溢出成负数。
     *   所以必须先转成 long 再计算。
     */
    public boolean isPerfectSquare(int num) {
        int left = 0;       // 左边界，最小可能的平方根
        int right = num;    // 右边界，最大可能的平方根（sqrt(num) 不会超过 num 本身）

        // 二分查找
        while (left <= right) {
            // 中点（写成 left + (right-left)/2 避免 left+right 溢出）
            int middle = left + (right - left) / 2;

            // 计算平方，强制转成 long 避免 int 溢出
            long product = (long) middle * middle;

            if (product < num) {
                // 平方太小，往右边找
                left = middle + 1;
            } else if (product > num) {
                // 平方太大，往左边找
                right = middle - 1;
            } else {
                // 找到完全平方数
                return true;
            }
        }

        // 循环结束还没返回，说明不是完全平方数
        return false;
    }
}
