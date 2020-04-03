package test;

/**
 * @author wangql
 * @since 2020-04-02 11:09
 */
public class BinarySearch {

  public static void main(String[] args) {
    int[] array = new int[10];
    for (int i = 0; i < array.length; i ++) {
      array[i] = i;
    }
    int i = binarySearch(5, array);
    System.out.println(i);
  }

  private static int binarySearch(int num, int[] array) {
    //init
    int low = 0, high = array.length - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (array[mid] == num) {return array[mid];}
      if (array[mid] > num) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }


  public int bSearch(int[] a, int n, int value) {
    int low = 0;
    int high = n - 1;
    while (low <= high) {
      int mid =  low + ((high - low) >> 1);
      if (a[mid] > value) {
        high = mid - 1;
      } else if (a[mid] < value) {
        low = mid + 1;
      } else {
        if ((mid == 0) || (a[mid - 1] != value)) return mid;
        else high = mid - 1;
      }
    }
    return -1;
  }
}
