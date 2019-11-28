package com.zy.sort;

import java.util.Arrays;

/**
 * 时间复杂度：O(nlogn) 空间复杂度：O(N)，稳定性：归并排序是稳定的排序算法. 归并排序需要一个与原数组相同长度的数组做辅助来排序 默认index前和后是有序的 然后前后比较取出放再另外一个相同大小的数组中(递归比较)
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
            int middle = (start + end) / 2;
            mergeSort(arrays, start, middle);
            mergeSort(arrays, middle + 1, end);
            merge(arrays, start, middle, end);
        }

    }

    private static void merge(int[] arrays, int low, int middle, int high) {
        int[] temp = new int[high - low + 1];
        int index = 0;
        int x = low;
        int y = middle + 1;
        while (x <= middle && y <= high) {
            if (arrays[x] <= arrays[y]) {
                temp[index] = arrays[x];
                x++;
            } else {
                temp[index] = arrays[y];
                y++;
            }
            index++;
        }
        if (x <= middle) {
            for (; x <= middle; x++) {
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
