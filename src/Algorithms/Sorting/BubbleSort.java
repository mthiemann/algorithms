package Algorithms.Sorting;

import Algorithms.Helpers.*;
import java.util.Scanner;

public class BubbleSort {

  /**
   * Sort array using BubbleSort
   * @param arr
   * @return the number of swaps needed to sort the array
   */
  public static int bubbleSort(int[] arr) {
    int counter = 0;
    int swaps = 0;
    int lastSwaps;

    while (counter < arr.length - 1) {
      lastSwaps = swaps;
      for (int i = 0; i < arr.length - 1 - counter; i++) {
        int cur = arr[i];
        int next = arr[i+1];
        if (cur > next) {
          arr[i+1] = cur;
          arr[i] = next;
          swaps++;
        }
      }
      if (lastSwaps == swaps) break; // array is already sorted
      counter++;
    }

    return swaps;
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
    int swaps = bubbleSort(arr);
    timer.stop();
    timer.printDuration();

    System.out.println("Array is sorted in " + swaps + " swaps.");
    Printer.print(arr);
  }
}
