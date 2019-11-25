package com.zy.sort;

import java.util.Arrays;

/**
 * 时间复杂度：O(nlogn) 空间复杂度：O(N)，归并排序需要一个与原数组相同长度的数组做辅助来排序 稳定性：归并排序是稳定的排序算法.
 * 
 * @author : 生态环境-张阳
 * @date : 2019/11/25 0025 13:48
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arrays = {1, 4, 5, 3, 2, 9, 8, 7, 10, 11, 16, 14};
        mergeSort(arrays, 0, arrays.length - 1);
        System.out.println(Arrays.toString(arrays));
    }

    private static void mergeSort(int[] arrays, int start, int end) {
        if (start < end) {
            int midle = (start + end) / 2;
            mergeSort(arrays, start, midle);
            mergeSort(arrays, midle + 1, end);
            merge(arrays, start, midle, end);
        }

    }

    private static void merge(int[] arrays, int low, int midle, int high) {
        int[] temp = new int[high - low + 1];
        int index = 0;
        int x = low;
        int y = midle + 1;
        while (x <= midle && y <= high) {
            if (arrays[x] <= arrays[y]) {
                temp[index] = arrays[x];
                x++;
            } else {
                temp[index] = arrays[y];
                y++;
            }
            index++;
        }
        if (x <= midle) {
            for (; x <= midle; x++) {
                temp[index] = arrays[x];
                index++;
            }
        } else if (y <= high) {
            for (; y <= high; y++) {
                temp[index] = arrays[y];
                index++;
            }
        }
        for (index = 0; index < temp.length; index++) {
            arrays[index + low] = temp[index];
        }
    }
}
