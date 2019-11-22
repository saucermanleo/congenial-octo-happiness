package com.zy.sort;

import java.util.Arrays;

/**
* 选择排序
*
* @author : 生态环境-张阳
* @date : 2019/11/22 0022 11:44
*/
public class SelectSort {
    public static void main(String[] args) {
        int[] arrays = {1,4,5,3,2,9,8,7,10,11,16,14};
        selectSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }
/**
* 选择排序.从第一个开始依次找到最小的值标记位置,然后交换标记的值.
*
* @param arrays
* @return : void
* @throws :
* @author : 生态环境-张阳
* @date : 2019/11/22 0022 11:45
*/
    private static void selectSort(int[] arrays) {
        int length = arrays.length;
        for(int i=0;i<length;i++){

            int index=i;
            for(int j=i+1;j<length;j++){
                if(arrays[j]<arrays[i]){
                    index = j;
                }
            }
            if(index!=i){
                int tmp = arrays[i];
                arrays[i]=arrays[index];
                arrays[index] = tmp;
            }


        }
    }
}
