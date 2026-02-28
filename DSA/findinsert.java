public class findinsert {
    public static void main(String[] args) {
     int arr[] ={2,3,5,7,8,9,10,56,};
    int first = 0;
    int last = arr.length-1;
    
    int key = 6;
    while(first<=last){
      int     mid = (first + last) /2;

         if(arr[mid] == key){
            System.out.println("element already inseted at" + mid);
            break;
         }
         else if(arr[mid]  < key){
                  first = mid+1;

    
         }
         else{
            last = mid-1;
    

         }

    }
    System.out.println("element should be placed in " + first);
    }
}
