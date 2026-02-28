public class binaryone {
    public static void main(String[] args) {
        int arr[] = {1,1,1,0,0};
        int first = 0;
        int last = arr.length-1;
        int red = 0;
         
        while(first<=last){
           int mid = (first+last) /2;
            if(arr[mid] ==1){
               red = mid;
               first = mid+1;

            }
            else{
                last = mid-1;
            }
            
        }
        System.out.println("count"+ (red+1));
    }
}
