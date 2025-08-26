package leetcode.arraylist.rotated_matrix;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int startX = 0, startY = 0;   // 每一圈的起点
        int offset = 1;               // 偏移量，控制边界收缩
        int loop = 0;                 // 当前圈数
        int i = 0, j = 0;
        int m = matrix.length;
        int n = matrix[0].length;

        // 只需要走 Math.min(m,n)/2 圈
        while (loop < Math.min(m, n) / 2) {
            // 1. 从左到右
            for (j = startY; j < n - offset; j++) {
                list.add(matrix[startX][j]);
            }

            // 2. 从上到下
            for (i = startX; i < m - offset; i++) {
                list.add(matrix[i][j]);
            }

            // 3. 从右到左
            for (; j > startY; j--) {
                list.add(matrix[i][j]);
            }

            // 4. 从下到上
            for (; i > startX; i--) {
                list.add(matrix[i][j]);
            }

            // 更新起点和边界
            startX++;
            startY++;
            offset++;
            loop++;
        }

        // 如果还剩最后一行（当 m <= n 且 m 是奇数时）
        if (m <= n && m % 2 == 1) {
            for (j = startY; j <= n - offset; j++) {
                list.add(matrix[startX][j]);
            }
        }
        // 如果还剩最后一列（当 n < m 且 n 是奇数时）
        else if (n < m && n % 2 == 1) {
            for (i = startX; i <= m - offset; i++) {
                list.add(matrix[i][startY]);
            }
        }

        return list;
    }
}
