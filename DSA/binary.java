public class binary {

        static void binarySearch(int s ,int l,int arr[],int search,int i){
            int mid = (s+l) /2;
            while(mid<i){
            
                if(arr[mid] == search){
                    System.out.println("found at " + mid);
                    break;
                }
                if(search>arr[mid]){
                    binarySearch(mid+1, l, arr, search, i);
                    break;
                }
                else{
                    binarySearch(s, mid-1, arr, search, i);
                    break;
                }

            }

        }
    public static void main(String[] args) {
        int arr[] = {2,3,4,5,6,7};
        int i = arr.length;
        int s = 0;
        int l = i-1;
        int search = 6;

        binarySearch(s,l,arr,search,i);
    }
}
