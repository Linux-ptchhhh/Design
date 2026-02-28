public class min {

    static int findmin(int[] arr){
        int min = arr[0];
        for(int i = 1;i<arr.length;i++){
            if(arr[i]<min){
                min = arr[i];
            }
        }
        return min;

    }
    public static void main(String[] args) {
        int arr[] = {4,2,7,8};
        int min = findmin(arr);
        int i = 0;
        while(arr[i] != min){
            
        }

    }
}
