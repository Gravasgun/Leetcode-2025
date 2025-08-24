package leetcode.arraylist.binary_search;

public class Leetcode_69 {
    /**
     * 使用二分查找计算一个非负整数 x 的算术平方根（向下取整）。
     * 例如：x=8，sqrt(8)=2（因为 2^2=4 <= 8，3^2=9 > 8）。
     *
     * 算法思路：
     * 1. 定义搜索区间 [0, x]；
     * 2. 每次取中点 middle，计算 middle^2 与 x 的大小关系；
     * 3. 如果 middle^2 == x，直接返回 middle；
     * 4. 如果 middle^2 < x，说明答案在右边，记录 middle 作为候选解，并继续往右搜索；
     * 5. 如果 middle^2 > x，说明答案在左边，缩小右边界；
     * 6. 直到区间收缩完毕，返回 result。
     *
     * 时间复杂度：O(log x)
     * 空间复杂度：O(1)
     *
     * 注意：需要用 long 来存放乘积，否则可能出现 int 溢出！
     */
    public int mySqrt(int x) {
        int left = 0;              // 左边界
        int right = x;             // 右边界（最大不会超过 x）
        int result = 0;            // 记录当前的解（向下取整），初始化为 0 比 Integer.MIN_VALUE 更合适

        while (left <= right) {
            // 用 (left + (right - left) / 2) 形式避免 (left + right) 直接相加可能溢出
            int middle = left + (right - left) / 2;

            /**
             * 这里一定要强转成 long 再相乘！
             * 如果直接写 middle * middle，在 int 范围内可能会溢出变成负数。
             * 举例：
             *    middle = 50000
             *    middle * middle = 2,500,000,000
             *    这个值超过 int 最大值 2,147,483,647，会溢出成负数
             *    最终 product 得到错误的结果，二分就会死循环
             */
            long product = (long) middle * middle;

            if (product > x) {
                // 平方太大，往左边收缩
                right = middle - 1;
            } else if (product < x) {
                // 平方太小，记录 middle 为候选答案，然后往右边收缩
                result = middle;
                left = middle + 1;
            } else {
                // 正好等于 x，直接返回
                return middle;
            }
        }
        // 循环退出时，result 保存的是 sqrt(x) 的向下取整
        return result;
    }
}
