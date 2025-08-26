package leetcode.arraylist.slide_window;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_904 {

    /**
     * 题目描述（Leetcode 904. 水果成篮）：
     *
     * 你正在一条一维的水果路线上，有一排果树，每棵树只会产出一种水果。
     * 给定一个整数数组 fruits，其中 fruits[i] 表示第 i 棵树产出的水果种类。
     *
     * 你有两个篮子，每个篮子只能装 **一种** 水果，
     * 但是每个篮子可以装 **任意数量** 的这种水果。
     *
     * 你从某棵树开始，沿着路往右走，**必须连续采摘**水果，
     * 一旦遇到不能放进篮子的水果，就必须停止。
     *
     * 问：你最多能采摘多少棵树上的水果？
     *
     * 示例：
     * 输入：fruits = [1,2,1]
     * 输出：3
     * 解释：你可以从树 0 开始，采摘 [1,2,1]。
     *
     * 输入：fruits = [0,1,2,2]
     * 输出：3
     * 解释：从树 1 开始，采摘 [1,2,2]。
     */

    public static int totalFruit(int[] fruits) {
        // 用 map 统计窗口中每种水果的数量
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int left = 0;                      // 滑动窗口左边界
        int result = Integer.MIN_VALUE;    // 记录最大长度（最好初始化为 0）

        // 遍历数组，相当于不断移动右边界
        for (int right = 0; right < fruits.length; right++) {
            // 如果当前水果不在 map 中，则新增
            if (!map.keySet().contains(fruits[right])) {
                map.put(fruits[right], 1);
            } else {
                // 如果已经存在，则数量 +1
                Integer count = map.get(fruits[right]);
                map.put(fruits[right], count + 1);
            }

            // 如果窗口中水果种类 > 2，就缩小窗口
            while (map.size() > 2) {
                Integer count = map.get(fruits[left]);
                map.put(fruits[left], count - 1); // 左边水果数量 -1
                if (map.get(fruits[left]) == 0) { // 如果数量为 0，则移出 map
                    map.remove(fruits[left]);
                }
                left++; // 左边界右移
            }

            // 更新最大长度
            result = Math.max(result, right - left + 1);
        }
        return result; // 返回最大能采摘的水果数
    }
}
