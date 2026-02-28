public class firstlast {

   static int leftIteration(int[] arr, int key) {

       int initial = 0;
       int high = arr.length - 1;
       int index = -1;

       while (initial <= high) {

           int mid = (initial + high) / 2;

           if (arr[mid] == key) {
               index = mid;
               high = mid - 1;
           }
           else if (arr[mid] < key) {
               initial = mid + 1;
           }
           else {
               high = mid - 1;
           }
       }
       return index;
   }

   static int rightIteration(int[] arr, int key) {

       int initial = 0;
       int high = arr.length - 1;
       int index = -1;

       while (initial <= high) {

           int mid = (initial + high) / 2;

           if (arr[mid] == key) {
               index = mid;
               initial = mid + 1;
           }
           else if (arr[mid] < key) {
               initial = mid + 1;
           }
           else {
               high = mid - 1;
           }
       }
       return index;
   }

   public static void main(String[] args) {

       int arr[] = {3,4,6,7,7,8,9};
       int key = 7;

       int first = leftIteration(arr, key);
       int last  = rightIteration(arr, key);

       System.out.println("First index = " + first);
       System.out.println("Last index  = " + last);
   }
}