package leetcode.arraylist;

public class MergeSort {

    // 主入口：对整个数组排序
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    }

    // 递归函数：对 arr[left..right] 进行归并排序
    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return; // base case：单个元素，无需排序

        int mid = left + (right - left) / 2;

        // 递归排序左右两半
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        // 合并左右两半
        merge(arr, left, mid, right);
    }

    // 合并两个有序子数组：arr[left..mid] 和 arr[mid+1..right]
    private static void merge(int[] arr, int left, int mid, int right) {
        // 创建临时数组用于存放合并结果
        int[] temp = new int[right - left + 1];
        int i = left;      // 左半部分指针
        int j = mid + 1;   // 右半部分指针
        int k = 0;         // temp 的指针

        // 把两个有序子数组合并成一个有序数组
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 如果左半部分还有剩余
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // 如果右半部分还有剩余
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 把排序后的 temp 复制回原数组 arr
        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // 测试函数
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 3, 9, 1, 6};
        mergeSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

