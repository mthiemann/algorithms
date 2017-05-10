package Sorting;

import java.util.Scanner;
import Helpers.*;


public class SelectionSort {

  /**
   * Prints array after every selection
   * @param arr
   */
  public static void selectionSort(int[] arr) {
    int min;
    for (int i = 0; i < arr.length - 1; i++) {
      min = i;
      for (int j = i+1; j < arr.length; j++) {
        if (arr[j] < arr[min]) {
          min = j;
        }
      }

      // swap
      int temp = arr[i];
      arr[i] = arr[min];
      arr[min] = temp;

      Printer.print(arr);
    }
  }

  public static void main(String args[] ) throws Exception {
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    int[] arr = new int[N];

    for (int i = 0; i < N; i++) {
      arr[i] = s.nextInt();
    }

    Timer timer = new Timer();
    timer.start();
    selectionSort(arr);
    timer.stop();
    timer.printDuration();
  }
}
