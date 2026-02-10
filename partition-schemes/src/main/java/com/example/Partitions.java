package com.example;


/*Partitions class inmplements lomuto and hoarse partition schemes in quicksort */


public final class Partitions {
    private Partitions() {}

    private static void swap(int[] a, int i, int j) {
        if (i == j) return;      // swapping same index does nothing
        int tmp = a[i];          // store a[i]
        a[i] = a[j];             // overwrite a[i]
        a[j] = tmp;              // write back stored value into a[j]
    }

    //Lomuto Partition Scheme, pivot choice a[high](which is last element in range)
    //after the partition, all elements <= pivot are moved to left, all elements > pivot are moved to right, pivot is placed in final correct pos.

    public static int lomutoPartition(int[] a, int low, int high){

        if(a == null) throw new IllegalArgumentException("array is null");

        //empty array or invalid range

        if(a.length == 0|| low > high) return -1;

        //bounds

        if (low < 0 || high >= a.length) throw new IllegalArgumentException("bad bounds");

        int pivot = a[high];
        int i = low;

        for(int j = low; j < high; j++){
            if(a[j] <= pivot){
                swap(a, i, j);
                i++;

            }
        }

        swap(a, i ,high);

        return i;

    }

    //hoare partition
    // pivot choice: a[mid] where mid = low + (high - low)/2

    public static int hoarePartition(int[] a, int low, int high) {
        if(a == null) throw new IllegalArgumentException("array is null");

        if (a.length == 0 || low > high) return -1;

        if (low < 0 || high >= a.length) throw new IllegalArgumentException("bad bounds");

        int pivot = a[low + (high - low) / 2];

        int i = low - 1;

        int j = high + 1;

        while(true) {


            do { i++; } while (a[i] < pivot);

            do { j--; } while (a[j] > pivot);

            if(i >= j) return j;

            swap(a, i, j);
        }
    }


}