package Algorithms.Sorting;

import Algorithms.Helpers.*;

import java.util.Scanner;

public class HeapSort {

  public static int getLeftChildIdx(int idx) {
    return 2 * idx + 1;
  }

  public static int getRightChildIdx(int idx) {
    return getLeftChildIdx(idx) + 1;
  }

  public static boolean isLeaf(int size, int idx) {
    return getLeftChildIdx(idx) >= size;
  }

  public static int getHighestChildIdx(int[] arr, int size, int idx) {
    int rightIdx = getRightChildIdx(idx);
    int leftIdx = getLeftChildIdx(idx);

    return (rightIdx >= size || arr[leftIdx] > arr[rightIdx]) ? leftIdx : rightIdx;
  }

  // assumption: left and right node are max heaps
  public static void maxHeapify(int[] arr, int size, int idx) {

    // while idx is not a leaf
    while(!isLeaf(size, idx)) {
      int highIdx = getHighestChildIdx(arr, size, idx);

      int node = arr[idx];
      int childNode = arr[highIdx];

      if (node < childNode) {
        arr[idx] = childNode;
        arr[highIdx] = node;
      } else {
        break;
      }

      idx = highIdx;
    }
  }

  public static void heapSort(int[] arr) {

    // initialize size of max heap
    int size = arr.length;
    if (size <= 1) return;

    // build max heap starting at first branch from the bottom right: (2 ^ floor(log2 size) - 2)
    double exponent = Math.floor(Math.log(size) / Math.log(2));
    int startNodeIdx = (int)Math.pow(2, exponent) - 2;

    for (int i = startNodeIdx; i >= 0; i--) {
      maxHeapify(arr, size, i);
    }

    for (int i = 0; i < arr.length - 1; i++) {
      // swap first and last element
      int temp = arr[size - 1];
      arr[size - 1] = arr[0];
      arr[0] = temp;

      size--;

      // call max heapify on top node to move new top element to the correct position
      maxHeapify(arr, size, 0);
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();

    int[] arr = new int[N];

    for(int i = 0; i < N; i++){
      arr[i] = in.nextInt();
    }

    Timer timer = new Timer();
    timer.start();
    heapSort(arr);
    timer.stop();
    timer.printDuration();

    Printer.print(arr);
  }
}
