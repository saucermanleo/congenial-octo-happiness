package com.zy.sort;

import java.util.Arrays;

/**
 * 插入排序 o(n) o(n^2) 默认index前面的值是有序的 从index+1处于前面的值比较,找到位置插入进相应的位置.
 * 
 * @author : 生态环境-张阳
 * @date : 2019/11/28 0028 16:03
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arrays = {1, 4, 5, 3, 2, 9, 8, 7, 10, 11, 16, 14};
        insertSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }

    private static void insertSort(int[] arrays) {
        int length = arrays.length;
        for (int i = 1; i < length; i++) {
            int tmp = arrays[i];
            int j = i;
            for (; j > 0 && tmp < arrays[j - 1]; j--) {
                arrays[j] = arrays[j - 1];
            }
            arrays[j] = tmp;
        }
    }
}
