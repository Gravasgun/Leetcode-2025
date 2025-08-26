package leetcode.arraylist.rotated_matrix;

public class Leetcode_59 {
    /**
     * Leetcode 59. 螺旋矩阵 II
     * 给定一个正整数 n，生成一个 n×n 的矩阵，数字从 1 到 n^2 按螺旋顺序填充。
     *
     * 思路：
     * - 从外层往内层一圈一圈地填充
     * - 每一圈有四个方向：从左到右、从上到下、从右到左、从下到上
     * - 填完一圈以后，起始位置右下移一格，offset 增加 1，开始下一圈
     * - 如果 n 是奇数，中间会有一个单独的格子要单独处理
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n]; // 结果矩阵
        int startX = 0; // 每一圈的起始行
        int startY = 0; // 每一圈的起始列
        int offset = 1; // 偏移量，用来确定每圈的边界
        int loop = 0;   // 当前已经完成的圈数
        int i = 0;      // 行索引
        int j = 0;      // 列索引
        int count = 1;  // 要填充的数字，从 1 开始

        // 总共需要填充 n/2 圈（如果 n 是奇数，中间会剩下一个单独元素）
        while (loop <= n / 2) {
            // 1. 从左到右，固定行 startX，列 j 递增
            for (j = startY; j < n - offset; j++) {
                matrix[startX][j] = count++;
            }

            // 2. 从上到下，固定列 j，行 i 递增
            for (i = startX; i < n - offset; i++) {
                matrix[i][j] = count++;
            }

            // 3. 从右到左，固定行 i，列 j 递减
            for (; j > startY; j--) {
                matrix[i][j] = count++;
            }

            // 4. 从下到上，固定列 j，行 i 递减
            for (; i > startX; i--) {
                matrix[i][j] = count++;
            }

            // 一圈完成后，起点下移右移，偏移量+1
            startY++;
            startX++;
            offset++;
            loop++;
        }

        // 如果 n 是奇数，填充矩阵中心点
        if (n % 2 == 1) {
            matrix[i][j] = count;
        }
        return matrix;
    }
}
