package com.zy.sort;

import java.util.Arrays;

/**
 * 基数排序 时间复杂度 O(n*w)。其中n为输入数组的长度。w为输入的数字中最长数字的长度。 空间复杂度 O(n)
 * 用10个数组 分别放余数为0,1,2...10的数 然后依次取出来就是有序的了(从个位到最高为循环n次)
 * 取出的时候为队列规则 fifo
 *
 * @author : 生态环境-张阳
 * @date : 2019/11/26 0026 8:58
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arrays = {111, 423, 545, 389, 200, 9234, 8567, 7454, 1012, 1135, 1667, 1445};
        radixSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }

    private static void radixSort(int[] arrays) {
        // 最大值
        int maxValue = arrays[0];
        for (int index = 0; index < arrays.length; index++) {
            if (arrays[index] > maxValue) {
                maxValue = arrays[index];
            }
        }
        int maxValueLength = (maxValue + "").length();
        int[][] bucket = new int[10][arrays.length];
        int bucketIndex[] = new int[10];

        for (int x = 0, n = 1; x < maxValueLength; x++, n = n * 10) {

            for (int y = 0; y < arrays.length; y++) {
                // 余数
                int remainder = arrays[y] / n % 10;
                bucket[remainder][bucketIndex[remainder]] = arrays[y];
                bucketIndex[remainder] = bucketIndex[remainder] + 1;
            }
            // arrays下标
            int h = 0;
            for (int k = 0; k < bucket.length; k++) {
                for (int j = 0; j < bucketIndex[k]; j++) {
                    arrays[h] = bucket[k][j];
                    h++;
                }
                bucketIndex[k] = 0;
            }

        }

    }
}
