package Sorting;

import java.util.Scanner;
import Helpers.*;

public class QuickSort {

  public static void quickSort(int[] arr) {
    quickSort(arr, 0, arr.length - 1);
  }

  public static void quickSort(int[] arr, int start, int end) {

    // base case: start >= end
    if (start >= end) return;

    // partition which returns the pivots index
    int pivIdx = partition(arr, start, end);

    // print array after partitioning
    Printer.print(arr);

    // call for left side
    quickSort(arr, start, pivIdx - 1);

    // call for right side
    quickSort(arr, pivIdx + 1, end);
  }

  public static int partition(int[] arr, int start, int end) {

    // initialize pivot index and pivot element
    int pivIdx = start;
    int pivot = arr[end];

      /* save m swap operations (swapping elements with themselves)
          (m = number of consecutive smaller than pivot elements at the beginning of the array) */
    boolean passedBigger = false;

    // iterate through arr
    for (int i = start; i < end; i++) {
      if (arr[i] <= pivot) {
        if (passedBigger) {
          // swap element with element at pivot index and increment pivot index
          int cached = arr[pivIdx];
          arr[pivIdx] = arr[i];
          arr[i] = cached;
        }
        pivIdx++;
      } else {
        passedBigger = true;
      }
    }

    // swap pivot element with the current element at the final pivot's position
    if (passedBigger) {
      arr[end] = arr[pivIdx];
      arr[pivIdx] = pivot;
    }

    // return pivotIndex
    return pivIdx;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    int[] arr = new int[N];

    for (int i = 0; i < N; i++) {
      arr[i] = scanner.nextInt();
    }
    Timer timer = new Timer();
    timer.start();
    quickSort(arr);
    timer.stop();
    timer.printDuration();
  }
}
