package leetcode.arraylist;

public class Leetcode_240 {
    /**
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     *
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 从二维数组的右上角开始搜索
        int row = 0;                            // 初始行索引为 0（第一行）
        int column = matrix[0].length - 1;      // 初始列索引为最右一列

        // 只要当前的行列索引没有越界，就继续循环
        while (row < matrix.length && column >= 0) {
            // 取当前单元格的值进行比较
            if (matrix[row][column] == target) {
                // 如果当前值等于目标值，直接返回 true
                return true;
            } else if (matrix[row][column] > target) {
                // 如果当前值大于目标值
                // 说明当前列的所有值都比目标值大（因为每一列是递增的）
                // 所以排除当前列，列索引向左移动
                column--;
            } else {
                // 如果当前值小于目标值
                // 说明当前行的所有值都比目标值小（因为每一行是递增的）
                // 所以排除当前行，行索引向下移动
                row++;
            }
        }
        // 如果跳出了 while 循环，说明整个数组都搜索过了，没找到目标值
        return false;
    }
}
