package Sorting;

import java.util.Scanner;
import Helpers.*;

public class InsertionSort {

  /**
   * Prints array after each correct insertion
   * @param ar
   */
  public static void insertionSort(int[] ar) {
      boolean inserted = false;
      int e;
      for (int i = 1; i < ar.length; i++) {
        e = ar[i];
        for (int j = i-1; j >= 0; j--) {
          if (ar[j] <= e) {
            ar[j+1] = e;
            inserted = true;
            break;
          } else {
            ar[j+1] = ar[j];
          }
        }
        if (!inserted) ar[0] = e;
        Printer.print(ar);
        inserted = false;
      }
    }

    public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      int s = in.nextInt();
      int[] ar = new int[s];
      for(int i = 0; i < s; i++){
        ar[i] = in.nextInt();
      }

      Timer timer = new Timer();
      timer.start();
      insertionSort(ar);
      timer.stop();
      timer.printDuration();
    }
}
