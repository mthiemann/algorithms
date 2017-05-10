import java.util.Scanner;
import Helpers.*;


class MergeSort {

  public static void mergeSort(int[] arr) {
    mergeSort(arr, 0, arr.length - 1);
  }

  public static void mergeSort(int[] arr, int start, int end) {
    if (start == end) return;

    int mid = start + (int) Math.floor((end - start) / 2);
    mergeSort(arr, start, mid);
    mergeSort(arr, mid + 1, end);

    // merge into array using previous start and end values
    int indexOne = start;
    int indexTwo = mid + 1;
    int index = 0;
    int[] sortedArr = new int[end - start + 1];

    // sort subarrays from last divide-conquer call into sortedArr
    while (indexOne <= mid || indexTwo <= end) {
      if (indexTwo > end) {
        sortedArr[index++] = arr[indexOne++];
        continue;
      }

      if (indexOne > mid) {
        sortedArr[index++] = arr[indexTwo++];
        continue;
      }

      sortedArr[index++] = (arr[indexOne] <= arr[indexTwo]) ? arr[indexOne++] : arr[indexTwo++];
    }

    // update main array: assign sorted subarray to corresponding part in main array
    for (int i = 0; i < sortedArr.length; i++) {
      arr[start + i] = sortedArr[i];
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
    mergeSort(arr);
    timer.stop();
    timer.printDuration();


    Printer.print(arr);
  }
}
