public class selection {
    //confusion one
    public static void main(String[] args) {
        int arr[] = {2,1,4,3};

        int i = 0;
        while(i<arr.length-1){
            int j = i+1;

           int min = i;
           
           if(arr[min]> arr[j]){
                 min = j;
           }
           int temp = arr[i];
           arr[min] =temp;
           arr[i] = temp;
           i++;
        }

        System.out.println("sorted array");
        for(int k =0;k<arr.length;k++){
            System.out.println(arr[k]);
        }
    }
}
