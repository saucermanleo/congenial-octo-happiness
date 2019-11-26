package com.zy.search;

import java.util.Arrays;

// 二分发查找 数组是有序的
public class BinarySearch {
    public int search(int target, int[] args) {
        Arrays.sort(args);
        int begin = 0;
        int end = args.length - 1;

        while (end >= begin) {

            int mid = (begin + end) / 2;
            if (args[mid] == target) {
                return mid;
            } else {
                if (args[mid] > target) {
                    end = mid - 1;
                } else {
                    begin = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arrays = {1, 2, 3, 4, 5, 6, 8, 9, 10, 11};
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.search(6, arrays));
    }
}
