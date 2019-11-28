package com.zy.sort;

import java.util.Arrays;

/**
 * 快速排序 平均时间复杂度nlogn 最坏n^2.
 * 
 * @author : 生态环境-张阳
 * @date : 2019/11/28 0028 16:04
 */
public class QucikSort {
    public static int partition(int[] arrs, int start, int end) {
        int tmp = arrs[start];
        int low = end;
        int begin = start;
        while (begin != low) {
            while (begin < low && arrs[low] > tmp) {
                low--;
            }
            if (begin < low) {
                arrs[begin] = arrs[low];
                begin++;
            }

            while (arrs[begin] < tmp && begin < low) {
                begin++;
            }
            if (begin < low) {
                arrs[low] = arrs[begin];
                low--;
            }
        }
        arrs[low] = tmp;
        return low;
    }

    public static void qucikSort(int[] arrs, int start, int end) {
        if (start < end) {
            int i = partition(arrs, start, end);
            qucikSort(arrs, i + 1, end);
            qucikSort(arrs, start, i - 1);
        }
    }

    public static void main(String[] args) {
        int[] arrays = {1, 4, 5, 3, 2, 9, 8, 7, 10, 11, 16, 14};
        qucikSort(arrays, 0, arrays.length - 1);
        System.out.println(Arrays.toString(arrays));
    }

}
