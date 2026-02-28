public class findminimum {
    public static void main(String[] args) {
        int arr[] = {4,5,6,7,8,3};
        int first = 0;
        int last = arr.length-1;
        int index = 0;
        while(first<=last){
            int mid = (first+last)/2;
            if(arr[mid]>arr[mid+1]){
                index = mid+1;
                break;
            }
            else{
                first = mid+1;

            }
        }
        System.out.println("smallest element in rotated array " + arr[index]);


        
    }
}
