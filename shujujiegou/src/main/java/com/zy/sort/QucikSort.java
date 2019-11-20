package com.zy.sort;

import java.util.Arrays;
//¿ìËÙÅÅĞò
public class QucikSort {
    public static int partition(int[] arrs,int start,int end){
        int tmp = arrs[start];
        int low = end;
        int begin = start;
        while(begin!=low){
            while(begin<low&&arrs[low]>tmp){
                low--;
            }
            if(begin<low){
                arrs[begin]=arrs[low];
                begin++;
            }

            while (arrs[begin]<tmp&&begin<low){
                begin++;
            }
            if(begin<low) {
                arrs[low] = arrs[begin];
                low--;
            }
        }
        arrs[low]= tmp;
        return low;
    }

    public static void qucikSort(int[] arrs,int start,int end){
        if(start<end) {
            int i = partition(arrs, start, end);
            qucikSort(arrs, i + 1, end);
            qucikSort(arrs, start, i - 1);
        }
    }

    public static void main(String[] args) {
        int[] arrays = {1,4,5,3,2,9,8,7,10,11,16,14};
        qucikSort(arrays,0,arrays.length-1);
        System.out.println(Arrays.toString(arrays));
    }

}
