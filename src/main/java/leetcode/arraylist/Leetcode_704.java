package leetcode.arraylist;

import java.util.Scanner;

/**
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 */
public class Leetcode_704 {

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入数组的长度：");
        int length = input.nextInt();
        System.out.println("请输入target：");
        int target = input.nextInt();
        int[] nums = new int[length];
        for (int i = 0; i < nums.length; i++) {
            System.out.println("请输入数组的元素：");
            nums[i] = input.nextInt();
        }
        System.out.print("目标元素的下标：" + search(nums, target));
    }
}
