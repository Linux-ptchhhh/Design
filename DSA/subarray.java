public class subarray {
    public static void main(String[] args) {
        int arr[] = {2,3,4,5};
        for(int i = 0;i<arr.length;i++){
            for(int j = i;j<arr.length;j++){
                for(int k = i;k<=j;k++){
                    System.out.println("subarrays "+arr[k]);
                }
            }
        }
    }
}
