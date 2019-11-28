package com.zy.sort;

import java.util.Arrays;

/**
 * 冒泡排序 从0开始两个相邻的位置比较,交换位置,一次循环数组末尾为最大值.
 * 
 * @author : 生态环境-张阳
 * @date : 2019/11/28 0028 15:29
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arrays = {1, 4, 5, 3, 2, 9, 8, 7, 10, 11, 16, 14};
        bubbleSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }

    private static void bubbleSort(int[] arrays) {
        int length = arrays.length;
        int temp;
        for (int x = 0; x < length - 1; x++) {
            for (int y = 0; y < length - x - 1; y++) {
                if (arrays[y] > arrays[y + 1]) {
                    temp = arrays[y + 1];
                    arrays[y + 1] = arrays[y];
                    arrays[y] = temp;
                }
            }
        }
    }
}
