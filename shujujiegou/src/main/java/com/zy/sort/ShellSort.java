package com.zy.sort;

import java.util.Arrays;

/**
 * 算法稳定性 -- 假设在数列中存在a[i]=a[j]，若在排序之前，a[i]在a[j]前面；并且排序之后，a[i]仍然在a[j]前面。则这个排序算法是稳定的！ 不稳地算法 O(N²) O(N3/2)
 * 通过num=length/2设置步长的插入排序 解决插入逆序时的高时间复杂度
 * 
 * @author : 生态环境-张阳
 * @date : 2019/11/28 0028 16:05
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arrays = {1, 4, 5, 3, 2, 9, 8, 7, 10, 11, 16, 14, 19, 6};
        shellSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }

    private static void shellSort(int[] arrays) {
        int length = arrays.length;
        int num;
        for (int i = 2; (num = length / i) >= 1; i = i << 1) {
            for (int j = 0; j < num; j++) {
                insertSort(num, j, arrays);
            }
        }
    }

    private static void insertSort(int step, int begin, int[] arrays) {
        for (int k = begin + step; k < arrays.length; k = k + step) {
            int tmp = arrays[k];
            int x = k;
            for (; x > begin && tmp < arrays[x - step]; x = x - step) {
                arrays[x] = arrays[x - step];
            }
            arrays[x] = tmp;
        }
    }
}
