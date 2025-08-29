package leetcode.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 【大体思路】
 * 题目要求找出数组中所有不重复的三元组 (nums[i], nums[j], nums[k])，使得三数之和为0。
 *
 * 解法步骤：
 * 1. 先对数组进行排序，方便使用双指针，并且有助于去重。
 * 2. 遍历数组，每次固定一个数 nums[i]，然后在剩余部分用双指针寻找另外两个数。
 * 3. 如果 nums[i] > 0，则三数之和不可能为0，直接结束循环。
 * 4. 使用左右指针：left = i+1, right = n-1。
 * - 若三数之和 > 0，则说明右指针太大，右移；
 * - 若三数之和 < 0，则说明左指针太小，左移；
 * - 若三数之和 = 0，加入结果，并移动指针同时去重。
 * 5. 去重处理：当固定的数或指针重复时，要跳过，避免重复解。
 * 6. 最终返回所有结果。
 */
public class Leetcode_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        // 存储所有符合条件的三元组
        List<List<Integer>> result = new ArrayList<>();

        // 1. 排序，便于双指针查找和去重
        Arrays.sort(nums);

        // 2. 遍历数组，固定第一个数 nums[i]
        for (int i = 0; i < nums.length - 2; i++) {
            // 剪枝：如果当前数 > 0，三数之和必然大于0，直接结束
            if (nums[i] > 0) {
                return result;
            }

            // 去重：避免固定数重复导致结果重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 定义左右指针
            int left = i + 1;              // 左指针
            int right = nums.length - 1;   // 右指针

            // 3. 开始双指针查找
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right]; // 当前三数之和

                if (sum > 0) {
                    // 和大于0，右指针左移（减小总和）
                    right--;
                } else if (sum < 0) {
                    // 和小于0，左指针右移（增大总和）
                    left++;
                } else {
                    // 找到一个符合条件的三元组
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳过重复的右指针元素
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // 跳过重复的左指针元素
                    while (right > left && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    // 移动指针，继续查找下一个解
                    right--;
                    left++;
                }
            }
        }

        // 4. 返回结果
        return result;
    }
}
